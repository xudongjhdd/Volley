package com.demo.volley.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;
import com.demo.volley.nethelp.MyImageLoader;
import java.io.ByteArrayOutputStream;

public class ToolImage {


	/**
	 * 下载图片
	 * 有缓存(内存缓存)
	 */
	public static void setImageLoader(ImageView imageView, String imageUrl, Context context, int defaultImageResId, int errorImageResId) {

		if (TextUtils.isEmpty(imageUrl)) {
			imageView.setImageResource(defaultImageResId);
			return;
		}
		//图片加载器
		MyImageLoader imageLoader = new MyImageLoader(VolleyUtil.getQueue(context), new LruImageCache());

		MyImageLoader.ImageContainer container;
		try {
			//如果当前ImageView上存在请求，先取消
			if (imageView.getTag() != null) {
				container = (MyImageLoader.ImageContainer) imageView.getTag();
				container.cancelRequest();
			}
		} catch (Exception e) {

		}
		MyImageLoader.ImageListener listener = MyImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
		container = imageLoader.get(imageUrl, listener);

		//在ImageView上存储当前请求的Container，用于取消请求
		imageView.setTag(container);

	}



	/**
	 * 位图转Base64
	 *
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToString(Bitmap bitmap) {

		// 将Bitmap转换成字符串

		String string = null;

		ByteArrayOutputStream bStream = new ByteArrayOutputStream();

		bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bStream);

		byte[] bytes = bStream.toByteArray();

		string = Base64.encodeToString(bytes, Base64.DEFAULT);

		return string;

	}


}
