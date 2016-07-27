package com.demo.volley.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.demo.volley.R;

public class LoadingDialog extends DialogFragment
{

	//private String mMsg = "Loading";
//	private String mMsg = "";
//
//	public void setMsg(String msg)
//	{
//		this.mMsg = msg;
//	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_loading, null);
//		TextView title = (TextView) view
//				.findViewById(R.id.id_dialog_loading_msg);
//		title.setText(mMsg);
		Dialog dialog = new Dialog(getActivity(), R.style.dialog);
		dialog.setContentView(view);
		return dialog;
	}

//	@Override
//	public void show(FragmentManager manager, String tag) {
//		if(ToolNetwork.isNetworkConnected(getActivity())){//无网络不显示
//			super.show(manager, tag);
//		}
//	}
//
//	@Override
//	public void dismiss() {
//		if(isAdded()) {
//			super.dismiss();
//		}
//	}
}
