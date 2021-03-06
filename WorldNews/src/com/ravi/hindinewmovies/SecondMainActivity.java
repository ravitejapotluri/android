package com.ravi.hindinewmovies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


























import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("NewApi")
public class SecondMainActivity extends Activity {

	private Button button;
	private TextView text;
	private EditText inputSearch;
	private String nameOfCountry;

	public void onCreate(Bundle savedInstanceState) {
		final Context context = this;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondactivity);
		final DatabaseHandler db = new DatabaseHandler(this);
		   AdView adView =   (AdView)this.findViewById(R.id.adView);
	        AdRequest adRequest = new AdRequest.Builder()
	       // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .build();
	     //   adView.setAdSize(AdSize.BANNER);
	        //adView.setAdUnitId("ca-app-pub-3730266544385182/1506030791");ca-app-pub-3730266544385182/1506030791
	       
	       // AdView adView = new AdView(this);
	       adView.loadAd(adRequest);
		List<WebLinks> links = null;
		
		
	
			links = db.getAllLinks(true);
			LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout1);
			TextView tv = (TextView)findViewById(R.id.country_text);   
			tv.setVisibility(View.GONE);
		
		/*final List<String> list = new ArrayList<String>();important*/
		final List<WebLinks> list2 = new ArrayList<WebLinks>();
		WebLinks[] data = null;
		//Log.d("countryname", countryname);
		for (WebLinks cn : links) {
			
			/*list.add(cn.get_name());*/
			list2.add(cn);

		}
		data = (WebLinks[])list2.toArray(new WebLinks[0]);
		final ListView listview = (ListView) findViewById(R.id.listView1);

		/*final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);!!!!!!important*/
		final ArrayAdapter<WebLinks> adapter2 = new ArrayAdapter<WebLinks>(this,
				android.R.layout.simple_list_item_1, list2);
		final OurArrayAdapter adapter3 = new OurArrayAdapter(this,
				R.layout.list_item, data);
		listview.setAdapter(adapter3);

		button = (Button) findViewById(R.id.buttonUrl);

		inputSearch = (EditText) findViewById(R.id.inputSearch);

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * Toast.makeText(getApplicationContext(),
				 * "Click ListItem Number " + position, Toast.LENGTH_LONG)
				 * .show();
				 */
				Intent intent = new Intent(context, WebViewActivity.class);
				Bundle b = new Bundle();
				
				
				String selection = parent.getItemAtPosition(position)
						.toString();
				
				WebLinks testLink = (WebLinks) parent.getItemAtPosition(position);
			
				
				WebLinks w = db.getLinkByID(String.valueOf(testLink.get_id()));

				b.putString("key", w.get_link());

				intent.putExtras(b);

				startActivity(intent);
			}
			
		});

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				adapter3.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

	}


	

}
/*
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondMainActivity extends Activity {
	EditText editText;
	Button button;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		final Context context = this;
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.secondactivity_main);

		editText= (EditText) findViewById(R.id.edit1);
		button= (Button) findViewById(R.id.btn);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				final String textVal = editText.getText().toString();
				Intent intent = new Intent(context, MainActivity.class);
				//Intent intent = new Intent(SecondMainActivity.class, MainActivity.class);

				
				intent.putExtra("countryname", textVal);


				startActivity(intent);
			}
		});

	}
}*/