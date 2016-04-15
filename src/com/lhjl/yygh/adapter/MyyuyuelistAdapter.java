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



/**
 * 
 * 
 * @author Administrator
 * 
 */
public class MyyuyuelistAdapter extends ArrayAdapter<HuanzhejiluInfo> {

	private ListView Mlist;
	// private Testload asyncImageLoader;
//	private AsyncImageLoader asyncImageLoader;
	Context context;
	public SharedPreferences sp;
	  private static LayoutInflater inflater=null;
	public MyyuyuelistAdapter(Activity activity,
			List<HuanzhejiluInfo> imageAndTexts, ListView Mlist) {
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
			rowView = inflater.inflate(R.layout.myyuyuelistitem, null);
			gdCache = new GdCache(rowView);
			rowView.setTag(gdCache);
		} else {
			gdCache = (GdCache) rowView.getTag();
		}
		HuanzhejiluInfo imageAndText = getItem(position);
		
		// Set the text on the TextView
		TextView hospital = gdCache.getHospital();
		TextView keshi = gdCache.getKeshi();
		TextView money = gdCache.getMoney();
		TextView data = gdCache.getDatatxt();
		TextView yisheng = gdCache.getYisheng();
		keshi.setText(imageAndText.getDepartmentItem());
		yisheng.setText(imageAndText.getDoctorName());
		money.setText(imageAndText.getRegistryFee());
		hospital.setText(imageAndText.getHospitalName());
		data.setText(imageAndText.getOrderDate());
		return rowView;
	}

	class GdCache {

		private View baseView;
		private TextView hospital;
		private ImageView imageView;
		private TextView keshi;
		private TextView yisheng;
		private TextView money;
		private TextView datatxt;
		public GdCache(View baseView) {
			this.baseView = baseView;
		}

		public TextView getDatatxt() {
			if (datatxt == null) {
				datatxt = (TextView) baseView.findViewById(R.id.datatxt);
			}
			return datatxt;
		}
		public TextView getHospital() {
			if (hospital == null) {
				hospital = (TextView) baseView.findViewById(R.id.hospital);
			}
			return hospital;
		}
		public TextView getKeshi() {
			if (keshi == null) {
				keshi = (TextView) baseView.findViewById(R.id.keshi);
			}
			return keshi;
		}
		public TextView getYisheng() {
			if (yisheng == null) {
				yisheng = (TextView) baseView.findViewById(R.id.yisheng);
			}
			return yisheng;
		}
		public TextView getMoney() {
			if (money == null) {
				money = (TextView) baseView.findViewById(R.id.money);
			}
			return money;
		}
//		public ImageView getImageView() {
//			if (imageView == null) {
//				imageView = (ImageView) baseView.findViewById(R.id.yisheng_img);
//			}
//			return imageView;
//		}
	}

}
