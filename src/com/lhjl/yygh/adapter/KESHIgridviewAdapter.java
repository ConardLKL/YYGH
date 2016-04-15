package com.lhjl.yygh.adapter;

import java.util.List;
import com.lhjl.yygh.R;
import com.lhjl.yygh.domain.DAkeshiListInfo;
import com.lhjl.yygh.domain.KeShiListInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * 
 * 
 * @author Administrator
 * 
 */
public class KESHIgridviewAdapter extends ArrayAdapter<KeShiListInfo> {

	private GridView gridView;
	// private Testload asyncImageLoader;
//	private AsyncImageLoader asyncImageLoader;
	Context context;
	public SharedPreferences sp;
	  private static LayoutInflater inflater=null;
	public KESHIgridviewAdapter(Activity activity,
			List<KeShiListInfo> imageAndTexts, GridView gridView) {
		super(activity, 0, imageAndTexts);
		this.gridView = gridView;
		 inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		 ImageCacheManager cacheMgr = new ImageCacheManager(activity);
//	        asyncImageLoader = new AsyncImageLoader(activity, cacheMgr.getMemoryCache(), cacheMgr.getPlacardFileCache());
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		sp = PreferenceManager.getDefaultSharedPreferences(activity);
		// Inflate the views from XML
		View rowView = convertView;
		GdCache gdCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.keshi_gridviewitem, null);
			gdCache = new GdCache(rowView);
			rowView.setTag(gdCache);
		} else {
			gdCache = (GdCache) rowView.getTag();
		}
		KeShiListInfo imageAndText = getItem(position);
		//Params.BASE_HTTP
//		String imageUrl = sp.getString(Params.BASE_HTTP, "") + imageAndText.getButton_url();
		ImageView imageView = gdCache.getImageView();
//		imageView.setTag(imageUrl);
//		if (imageAndText.getButton_url() == null) {
//
//			imageView.setImageResource(R.drawable.nopic);
//		} else {
//			 Bitmap bmp = asyncImageLoader.loadBitmap(imageView, imageUrl, true);
//			if (bmp == null) {
//				imageView.setImageResource(R.drawable.nopic);
//			} else {
//				// imageView.setImageDrawable(cachedImage);
//				imageView.setImageBitmap(bmp);
//
//			}
//		}
		TextView text1 = gdCache.gettxt();
		if(imageAndText.getDepartmentName().equals("内科")){
			imageView.setImageResource(R.drawable.xinnei);
			//#b90b0a
			text1.setTextColor(0XFFb90b0a);
		}else if(imageAndText.getDepartmentName().equals("外科")){
			imageView.setImageResource(R.drawable.puwai);
			text1.setTextColor(0XFF0a8e51);
		}else if(imageAndText.getDepartmentName().equals("妇产科")){
			//#b90b0a
			imageView.setImageResource(R.drawable.fuchan);
			text1.setTextColor(0XFFfa8100);
		}else if(imageAndText.getDepartmentName().equals("神经科")){
			imageView.setImageResource(R.drawable.shenjing);
			text1.setTextColor(0XFF1083cf);
		}else if(imageAndText.getDepartmentName().equals("耳鼻喉科")){
			imageView.setImageResource(R.drawable.erbihou);
			text1.setTextColor(0XFFb88c17);
		}else if(imageAndText.getDepartmentName().equals("儿科")){
			text1.setTextColor(0XFFfa8100);
			imageView.setImageResource(R.drawable.erke);
		}else if(imageAndText.getDepartmentName().equals("口腔科")){
			text1.setTextColor(0XFF0a8e51);
			imageView.setImageResource(R.drawable.kouqiang);
		}else if(imageAndText.getDepartmentName().equals("乳腺科")){
			text1.setTextColor(0XFFb90b0a);
			imageView.setImageResource(R.drawable.ruxian);
		}else if(imageAndText.getDepartmentName().equals("骨科")){
			imageView.setImageResource(R.drawable.guke);
			text1.setTextColor(0XFFb88c17);
		}else if(imageAndText.getDepartmentName().equals("眼科")){
			imageView.setImageResource(R.drawable.yanke);
			text1.setTextColor(0XFF897401);
		}
		// Set the text on the TextView
	
		text1.setText(imageAndText.getDepartmentName());
		return rowView;
	}

	class GdCache {

		private View baseView;
		private TextView txt;
		private ImageView imageView;
		private TextView txtcode;
		public GdCache(View baseView) {
			this.baseView = baseView;
		}

//		public TextView getTxtcode() {
//			if (txtcode == null) {
//				txtcode = (TextView) baseView.findViewById(R.id.txtcode);
//			}
//			return txtcode;
//		}
		public TextView gettxt() {
			if (txt == null) {
				txt = (TextView) baseView.findViewById(R.id.keshi_text);
			}
			return txt;
		}

		public ImageView getImageView() {
			if (imageView == null) {
				imageView = (ImageView) baseView.findViewById(R.id.keshi_img);
			}
			return imageView;
		}
	}

}
