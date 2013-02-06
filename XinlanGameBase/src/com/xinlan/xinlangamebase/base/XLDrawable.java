package com.xinlan.xinlangamebase.base;

public class XLDrawable extends Mesh {
	protected float width;
	protected float height;

	public XLDrawable(float _x, float _y, float _z, float _width, float _height) {
		this.x = _x;
		this.y = _y;
		this.z = _z;
		this.width = _width;
		this.height = _height;
		float textureCoordinates[] = { 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 0.0f, };
		short[] indices = new short[] { 0, 1, 2, 1, 3, 2 };
		float[] vertices = new float[] { 0.0f, 0.0f, 0.0f, width, 0.0f, 0.0f,
				0.0f, height, 0.0f, width, height, 0.0f };
		setIndices(indices);
		setVertices(vertices);
		setTextureCoordinates(textureCoordinates);
	}

	public void setWidth(int width) {
		this.width = width;
		float[] vertices = new float[] { 0, 0, 0, width, 0, 0.0f, 0, height,
				0.0f, width, height, 0.0f };
		setVertices(vertices);
	}

	public void setHeight(int height) {
		this.height = height;
		float[] vertices = new float[] { 0, 0, 0, width, 0, 0.0f, 0, height,
				0.0f, width, height, 0.0f };
		setVertices(vertices);
	}
}// end class
