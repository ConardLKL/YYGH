package com.lhjl.yygh.adapter;

import java.util.List;

import com.lhjl.yygh.R;
import com.lhjl.yygh.domain.DAkeshiListInfo;
import com.lhjl.yygh.domain.KeShiListInfo;
import com.lhjl.yygh.domain.YiYuanListInfo;

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
public class YuanQuAdapter extends ArrayAdapter<YiYuanListInfo> {

	private GridView gridView;
	// private Testload asyncImageLoader;
//	private AsyncImageLoader asyncImageLoader;
	Context context;
	public SharedPreferences sp;
	  private static LayoutInflater inflater=null;
	public YuanQuAdapter(Activity activity,
			List<YiYuanListInfo> imageAndTexts, GridView gridView) {
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
			rowView = inflater.inflate(R.layout.yiyuangriditem, null);
			gdCache = new GdCache(rowView);
			rowView.setTag(gdCache);
		} else {
			gdCache = (GdCache) rowView.getTag();
		}
		YiYuanListInfo imageAndText = getItem(position);
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
		if(position==0){
			imageView.setBackgroundResource(R.drawable.daliansanyuan);
		}else if(position==1){
			imageView.setBackgroundResource(R.drawable.yidayiyuan);
		}else {
			imageView.setBackgroundResource(R.drawable.yidaeryuan);
		}
		// Set the text on the TextView
		TextView text1 = gdCache.gettxt();
		text1.setText(imageAndText.getHospitalName());
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
				txt = (TextView) baseView.findViewById(R.id.yiyuan_text);
			}
			return txt;
		}

		public ImageView getImageView() {
			if (imageView == null) {
				imageView = (ImageView) baseView.findViewById(R.id.yiyuan_img);
			}
			return imageView;
		}
	}

}
