package com.xinlan.xinlangamebase.base;

/**
 * Copyright 2010 Per-Erik Bergman (per-erik.bergman@jayway.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Group extends Mesh {
	private final Vector<Mesh> mChildren = new Vector<Mesh>();

	@Override
	public void draw(GL10 gl) {
		if (!shouldBeDrawn) return;
		int size = mChildren.size();
		for (int i = 0; i < size; i++) {
			mChildren.get(i).draw(gl);
		}
			
		gl.glPopMatrix();
	}

	public void add(int location, Mesh object) {
		mChildren.add(location, object);
	}

	public boolean add(Mesh object) {
		return mChildren.add(object);
	}

	public void clear() {
		mChildren.clear();
	}

	public Mesh get(int location) {
		return mChildren.get(location);
	}

	public Mesh remove(int location) {
		return mChildren.remove(location);
	}

	public boolean remove(Object object) {
		return mChildren.remove(object);
	}

	public int size() {
		return mChildren.size();
	}

}
