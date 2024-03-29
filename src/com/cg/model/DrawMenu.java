package com.cg.model;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import static javax.media.opengl.GL.*; // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

/**
 * JOGL 2.0 Example 2: Rotating 3D Shapes (GLCanvas)
 */
@SuppressWarnings("serial")
public class DrawMenu extends GLCanvas implements GLEventListener {

	// Setup OpenGL Graphics Renderer

	private GLU glu; // for the GL Utility
	// private float anglePyramid = 0; // rotational angle in degree for pyramid
	// private float angleCube = 0; // rotational angle in degree for cube
	private float rotateOb = 0.5f; // rotational speed for pyramid
	// private float speedCube = -1.5f; // rotational speed for cube
	private Texture[] textures = new Texture[2];

	private float angleSphere;
	private String earthTextureFileName = "/pic/Color Map.jpg";
	private String comingSoonTexture = "/pic/mars_surface_vik1_big.jpg";
	private String earth = "Earth";
	private String mars = "Mars";

	// private String textureFileName = "pic/Color Map.jpg";

	/** Constructor to setup the GUI for this Component */
	public DrawMenu() {
		this.addGLEventListener(this);
	}

	// ------ Implement methods declared in GLEventListener ------

	/**
	 * Called back immediately after the OpenGL context is initialized. Can be
	 * used to perform one-time initialization. Run only once.
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context
		glu = new GLU(); // get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f); // set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL_LESS); // the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best
																// perspective
																// correction
		gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out
									// lighting
		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float position[] = { 0.0f, 3.0f, 2.0f, 0.0f };
		float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
		float local_view[] = { 0.0f };

		gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL_LIGHT0, GL_POSITION, position, 0);
		gl.glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
		gl.glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);

		gl.glEnable(GL_LIGHTING);
		gl.glEnable(GL_LIGHT0);
		try {
			textures[0] = TextureIO.newTexture(this.getClass().getResourceAsStream(earthTextureFileName), true, "jpg");
			textures[1] = TextureIO.newTexture(this.getClass().getResourceAsStream(comingSoonTexture), true, "jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Call-back handler for window re-size event. Also called when the drawable
	 * is first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context

		if (height == 0)
			height = 1; // prevent divide by zero
		float aspect = (float) width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 1000.0); // fovy, aspect, zNear,
														// zFar
		glu.gluLookAt(0, 7, 7, 0, 0, 0, 0, 1, 0);
		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}

	/**
	 * Called back by the animator to perform rendering.
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		GLUT glut = new GLUT();
		//
		float no_mat[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float mat_ambient[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float mat_ambient_color[] = { 0.8f, 0.8f, 0.2f, 1.0f };
		float mat_diffuse[] = { 0.1f, 0.5f, 0.8f, 1.0f };
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float no_shininess[] = { 0.0f };
		float low_shininess[] = { 5.0f };
		float high_shininess[] = { 100.0f };
		float mat_emission[] = { 0.3f, 0.2f, 0.2f, 0.0f };

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// textRenderer.begin3DRendering();
		/*
		 * draw sphere in first row, first column diffuse reflection only; no
		 * ambient or specular
		 */
		gl.glPushMatrix();
		gl.glTranslatef(-2.0f, 2.0f, 0.0f);
		gl.glRotatef(angleSphere, 0.0f, 1.0f, 0.0f);
		textures[0].enable(gl);
		textures[0].bind(gl);
		GLUquadric earth = glu.gluNewQuadric();
		glu.gluQuadricTexture(earth, true);
	    glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
	    glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
	    glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
	    final float radius = 1.5f;
	    final int slices = 16;
	    final int stacks = 16;
	    glu.gluSphere(earth, radius, slices, stacks);
	    gl.glMaterialfv(GL.GL_FRONT, GL_AMBIENT, mat_ambient_color, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_SPECULAR, mat_specular, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_SHININESS, high_shininess, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_EMISSION, no_mat, 0);
	    //glut.glutSolidSphere(1.0, 20, 20);
	    glu.gluDeleteQuadric(earth);
		gl.glPopMatrix();
		/*
		 * draw sphere in third row, fourth column colored ambient and diffuse
		 * reflection; emission; no specular
		 */
		gl.glPushMatrix();
		gl.glTranslatef(2.0f, 0.0f, 1.0f);
		gl.glRotatef(angleSphere, 0.0f, 1.0f, 0.0f);
		//		glut.glutSolidSphere(1.0, 20, 20);
		textures[1].enable(gl);
		textures[1].bind(gl);
		GLUquadric mar = glu.gluNewQuadric();
		glu.gluQuadricTexture(mar, true);
	    glu.gluQuadricDrawStyle(mar, GLU.GLU_FILL);
	    glu.gluQuadricNormals(mar, GLU.GLU_FLAT);
	    glu.gluQuadricOrientation(mar, GLU.GLU_OUTSIDE);
	    final float radiusm = 1.5f;
	    final int slicesm = 16;
	    final int stacksm = 16;
	    glu.gluSphere(mar, radiusm, slicesm, stacksm);
	    gl.glMaterialfv(GL.GL_FRONT, GL_AMBIENT, mat_ambient_color, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_SPECULAR, no_mat, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_SHININESS, no_shininess, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL_EMISSION, mat_emission, 0);
	    glu.gluDeleteQuadric(mar);
		gl.glPopMatrix();
		angleSphere += rotateOb;
		gl.glFlush();
	}

	/**
	 * Called back before the OpenGL context is destroyed. Release resource such
	 * as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}
}