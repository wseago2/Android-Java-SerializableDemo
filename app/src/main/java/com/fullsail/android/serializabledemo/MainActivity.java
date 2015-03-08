package com.fullsail.android.serializabledemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final String FILENAME = "test.txt";

	EditText mEdit;
	TextView mChanges;
	DataHolder mHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEdit = (EditText)findViewById(R.id.edit);
		mChanges = (TextView)findViewById(R.id.changes);
		
		findViewById(R.id.load).setOnClickListener(this);
		findViewById(R.id.save).setOnClickListener(this);
		mHolder = null;
	}
	
	private void writeToFile(String _filename, String _data) {
		File external = getExternalFilesDir(null);
		File file = new File(external, _filename);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			if(mHolder == null) {
				mHolder = new DataHolder();
			}
			mHolder.setData(_data);
			
			oos.writeObject(mHolder);
			oos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String readFromFile(String _filename) {
		File external = getExternalFilesDir(null);
		File file = new File(external, _filename);
		
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(fin);
			
			mHolder = (DataHolder)oin.readObject();
			
			oin.close();
			return mHolder.getData();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void onClick(View _v) {
		if(_v.getId() == R.id.save) {
			writeToFile(FILENAME, mEdit.getText().toString());
		} else {
			String text = readFromFile(FILENAME);
			if(text != null) {
				mEdit.setText(text);
				mChanges.setText("" + mHolder.getChanges());
			}
		}
	}
}
