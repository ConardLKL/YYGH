package com.lhjl.yygh.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lhjl.yygh.R;
import com.lhjl.yygh.domain.YiShengListInfo;



/**
 * 
 * 
 * @author Administrator
 * 
 */
public class YishengListadapter extends ArrayAdapter<YiShengListInfo> {

	private ListView Mlist;
	// private Testload asyncImageLoader;
//	private AsyncImageLoader asyncImageLoader;
	Context context;
	public SharedPreferences sp;
	private String keshi_txt;
	  private static LayoutInflater inflater=null;
	public YishengListadapter(Activity activity,
			List<YiShengListInfo> imageAndTexts, ListView Mlist, String keshi_txt) {
		super(activity, 0, imageAndTexts);
		this.Mlist = Mlist;
		this.keshi_txt = keshi_txt;
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
			rowView = inflater.inflate(R.layout.yisheng_listitem, null);
			gdCache = new GdCache(rowView);
			rowView.setTag(gdCache);
		} else {
			gdCache = (GdCache) rowView.getTag();
		}
		YiShengListInfo imageAndText = getItem(position);
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
		if(imageAndText.getDoctorName().equals("王蓉")){
			imageView.setBackgroundResource(R.drawable.doc1);
		}else if(imageAndText.getDoctorName().equals("周翔宇")){
			imageView.setBackgroundResource(R.drawable.doc2);
		}else if(imageAndText.getDoctorName().equals("李响")){
			imageView.setBackgroundResource(R.drawable.doc3);
		}else if(imageAndText.getDoctorName().equals("王益")){
			imageView.setBackgroundResource(R.drawable.doc4);
		}else if(imageAndText.getDoctorName().equals("徐建伟")){
			imageView.setBackgroundResource(R.drawable.doc5);
		}else if(imageAndText.getDoctorName().equals("王晓晓")){
			imageView.setBackgroundResource(R.drawable.doc9);
		}else if(imageAndText.getDoctorName().equals("牛壮")){
			imageView.setBackgroundResource(R.drawable.doc5);
		}else if(imageAndText.getDoctorName().equals("吴霞")){
			imageView.setBackgroundResource(R.drawable.doc6);
		}else if(imageAndText.getDoctorName().equals("赵猛")){
			imageView.setBackgroundResource(R.drawable.doc8);
		}else if(imageAndText.getDoctorName().equals("王宏")){
			imageView.setBackgroundResource(R.drawable.doc2);
		}else if(imageAndText.getDoctorName().equals("徐淼")){
			imageView.setBackgroundResource(R.drawable.doc3);
		}else if(imageAndText.getDoctorName().equals("孟晓辉")){
			imageView.setBackgroundResource(R.drawable.doc5);
		}else if(imageAndText.getDoctorName().equals("郝仁")){
			imageView.setBackgroundResource(R.drawable.doc2);
		}else if(imageAndText.getDoctorName().equals("孙嘉")){
			imageView.setBackgroundResource(R.drawable.doc1);
		}else if(imageAndText.getDoctorName().equals("赵颖")){
			imageView.setBackgroundResource(R.drawable.doc7);
		}else if(imageAndText.getDoctorName().equals("李健")){
			imageView.setBackgroundResource(R.drawable.doc5);
		}else if(imageAndText.getDoctorName().equals("朱伟")){
			imageView.setBackgroundResource(R.drawable.doc8);
		}else if(imageAndText.getDoctorName().equals("牛立伟")){
			imageView.setBackgroundResource(R.drawable.doc2);
		}else if(imageAndText.getDoctorName().equals("葛珊珊")){
			imageView.setBackgroundResource(R.drawable.doc3);
		}else {
			imageView.setBackgroundResource(R.drawable.doc5);
		}
		// Set the text on the TextView
		TextView text1 = gdCache.gettxt();
		TextView keshi = gdCache.getKeshi();
		TextView type = gdCache.getType();
		TextView yisheng_feiyong = gdCache.getYisheng_feiyong();
		yisheng_feiyong.setText(imageAndText.getFee());
		keshi.setText(keshi_txt);
		text1.setText(imageAndText.getDoctorName());
		type.setText(imageAndText.getSessionType());
		return rowView;
	}

	class GdCache {

		private View baseView;
		private TextView txt;
		private ImageView imageView;
		private TextView keshi;
		private TextView type;
		private TextView yisheng_feiyong;
		public GdCache(View baseView) {
			this.baseView = baseView;
		}
		public TextView getYisheng_feiyong() {
			if (yisheng_feiyong == null) {
				yisheng_feiyong = (TextView) baseView.findViewById(R.id.yisheng_feiyong);
			}
			return yisheng_feiyong;
		}
		public TextView getType() {
			if (type == null) {
				type = (TextView) baseView.findViewById(R.id.yisheng_type);
			}
			return type;
		}
		
		public TextView getKeshi() {
			if (keshi == null) {
				keshi = (TextView) baseView.findViewById(R.id.yisheng_keshi);
			}
			return keshi;
		}
		public TextView gettxt() {
			if (txt == null) {
				txt = (TextView) baseView.findViewById(R.id.yisheng_name);
			}
			return txt;
		}

		public ImageView getImageView() {
			if (imageView == null) {
				imageView = (ImageView) baseView.findViewById(R.id.yisheng_img);
			}
			return imageView;
		}
	}

}
