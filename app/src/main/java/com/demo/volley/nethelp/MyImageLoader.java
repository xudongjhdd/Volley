package com.demo.volley.nethelp;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;



/**
 * Author：xudong
 * Date: 16/6/14 19:33
 */
public class MyImageLoader extends ImageLoader {

	private ImageCache imageCache;

	/**
	 * Constructs a new ImageLoader.
	 *
	 * @param queue      The RequestQueue to use for making image requests.
	 * @param imageCache The cache to use as an L1 cache.
	 */
	public MyImageLoader(RequestQueue queue, ImageCache imageCache) {
		super(queue, imageCache);
		this.imageCache = imageCache;
	}


//    @Override
//    public ImageContainer get(String requestUrl, ImageListener imageListener, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
//        return super.get(requestUrl, imageListener, maxWidth, maxHeight, scaleType);
//    }


	@Override
	protected Request<Bitmap> makeImageRequest(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, final String cacheKey) {
		//return super.makeImageRequest(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);

		return new MyImageRequest(requestUrl, new Response.Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				onGetImageSuccess(cacheKey, response);
			}
		}, maxWidth, maxHeight, Bitmap.Config.RGB_565, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				onGetImageError(cacheKey, error);
			}
		});

	}


}
