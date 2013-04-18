package cn.eoe.leigo.popup.window;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.eoe.leigo.popup.window.adapter.GroupAdapter;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private GroupAdapter groupAdapter;
	private ArrayList<String> groups;
	private TextView mHomeNameTextView;
	private PopupWindow mPopupWindow;
	private View contentView;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mHomeNameTextView = (TextView) findViewById(R.id.tv_home_name);
		mHomeNameTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		showPopwindow(v);
	}

	private void showPopwindow(View parent) {
		if (mPopupWindow == null) {
			LayoutInflater mLayoutInflater = LayoutInflater.from(this);
			contentView = mLayoutInflater.inflate(R.layout.group_list, null);

			listView = (ListView) contentView.findViewById(R.id.lv_group);

			// ��������
			groups = new ArrayList<String>();

			groups.add("ʱ������");

			groups.add("��������");

			groups.add("�ҵ�΢��");

			groups.add("����Ȧ");

			groups.add("���Ĺ�ע");

			groups.add("�ܱ�");

			groupAdapter = new GroupAdapter(this, groups);
			listView.setAdapter(groupAdapter);

			mPopupWindow = new PopupWindow(contentView, getWindowManager()
					.getDefaultDisplay().getWidth() / 2, 250);
		}
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setFocusable(true);

		// ��ʾ��λ��Ϊ:��Ļ�Ŀ�ȵ�1/16
		int xPos = getWindowManager().getDefaultDisplay().getWidth() / 16;

		mPopupWindow.showAsDropDown(parent, xPos, 0);

		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mHomeNameTextView.setText(groups.get(position));

		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}
