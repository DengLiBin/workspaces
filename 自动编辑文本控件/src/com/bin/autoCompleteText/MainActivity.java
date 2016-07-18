package com.bin.autoCompleteText;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	
	String[] books=new String[]{"·è¿ñjava½²Òå","·è¿ñAjax½²Òå","·è¿ñXML½²Òå"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,books);
		
		AutoCompleteTextView autoText=(AutoCompleteTextView) findViewById(R.id.auto_text);
		
		autoText.setAdapter(aa);
	}
	
}
