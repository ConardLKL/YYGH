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
import com.lhjl.yygh.domain.HuanzhejiluInfo;
import com.lhjl.yygh.domain.YiShengListInfo;
import com.lhjl.yygh.domain.YiYuanListInfo;



/**
 * 
 * 
 * @author Administrator
 * 
 */
public class YiYuanlistAdapter extends ArrayAdapter<YiYuanListInfo> {

	private ListView Mlist;
	// private Testload asyncImageLoader;
//	private AsyncImageLoader asyncImageLoader;
	Context context;
	public SharedPreferences sp;
	  private static LayoutInflater inflater=null;
	public YiYuanlistAdapter(Activity activity,
			List<YiYuanListInfo> imageAndTexts, ListView Mlist) {
		super(activity, 0, imageAndTexts);
		this.Mlist = Mlist;
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
			rowView = inflater.inflate(R.layout.yiyuanlistitem, null);
			gdCache = new GdCache(rowView);
			rowView.setTag(gdCache);
		} else {
			gdCache = (GdCache) rowView.getTag();
		}
		YiYuanListInfo imageAndText = getItem(position);
		
		// Set the text on the TextView
		ImageView img = gdCache.getImageView();
		if(position==0){
			img.setBackgroundResource(R.drawable.yy1);
		}else if(position==1){
			img.setBackgroundResource(R.drawable.yy2);
		}else if(position==2){
			img.setBackgroundResource(R.drawable.yy3);
		}
		TextView hospital = gdCache.getHospital();
		TextView dengji = gdCache.getDengji();
		TextView addr = gdCache.getAddr();
//		TextView data = gdCache.getDatatxt();
//		TextView yisheng = gdCache.getYisheng();
		dengji.setText(imageAndText.getHospital_dengji());
		addr.setText(imageAndText.getHospital_addr());
		hospital.setText(imageAndText.getHospitalName());
		return rowView;
	}

	class GdCache {

		private View baseView;
		private TextView hospital;
		private ImageView imageView;
		private TextView addr;
		private TextView dengji;
//		private TextView money;
//		private TextView datatxt;
		public GdCache(View baseView) {
			this.baseView = baseView;
		}

//		public TextView getDatatxt() {
//			if (datatxt == null) {
//				datatxt = (TextView) baseView.findViewById(R.id.datatxt);
//			}
//			return datatxt;
//		}
		public TextView getHospital() {
			if (hospital == null) {
				hospital = (TextView) baseView.findViewById(R.id.hospitalname1);
			}
			return hospital;
		}
		public TextView getAddr() {
			if (addr == null) {
				addr = (TextView) baseView.findViewById(R.id.hospitaladdr);
			}
			return addr;
		}
		public TextView getDengji() {
			if (dengji == null) {
				dengji = (TextView) baseView.findViewById(R.id.hospitaldengji);
			}
			return dengji;
		}
//		public TextView getMoney() {
//			if (money == null) {
//				money = (TextView) baseView.findViewById(R.id.money);
//			}
//			return money;
//		}
		public ImageView getImageView() {
			if (imageView == null) {
				imageView = (ImageView) baseView.findViewById(R.id.yiyuan_img);
			}
			return imageView;
		}
	}

}
