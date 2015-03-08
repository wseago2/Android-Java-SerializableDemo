package com.fullsail.android.serializabledemo;

import java.io.Serializable;

public class DataHolder implements Serializable {

	private static final long serialVersionUID = -7791154359356162736L;

	private String mData;
	private int mChanges;
	
	public DataHolder() {
		mData = "";
		mChanges = 0;
	}
	
	public void setData(String _data) {
		mData = _data;
		mChanges++;
	}
	
	public String getData() {
		return mData;
	}
	
	public int getChanges() {
		return mChanges;
	}
}