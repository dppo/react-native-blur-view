package com.blur;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.BlurViewManagerDelegate;
import com.facebook.react.viewmanagers.BlurViewManagerInterface;

import java.util.Objects;

import eightbitlab.com.blurview.BlurView;

@ReactModule(name = BlurViewManager.NAME)
public class BlurViewManager extends SimpleViewManager<BlurView> implements BlurViewManagerInterface<BlurView> {

  public static final String NAME = "BlurView";
  public static final int defaultRadius = 15;
  public static final int lightOverlayColor = Color.argb(80, 255, 255, 255);
  public static final int darkOverlayColor = Color.argb(160, 16, 12, 12);

  private final ViewManagerDelegate<BlurView> mDelegate;

  public BlurViewManager() {
    mDelegate = new BlurViewManagerDelegate(this);
  }

  @Nullable
  @Override
  protected ViewManagerDelegate<BlurView> getDelegate() {
    return mDelegate;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public BlurView createViewInstance(ThemedReactContext context) {
    View decorView = Objects.requireNonNull(context.getCurrentActivity()).getWindow().getDecorView();
    ViewGroup rootView = decorView.findViewById(android.R.id.content);

    BlurView blurView = new BlurView(context);
    blurView.setupWith(rootView).setFrameClearDrawable(decorView.getBackground()).setBlurRadius(defaultRadius);

    blurView.setOverlayColor(lightOverlayColor);
    blurView.setBlurEnabled(true);
    blurView.setBlurAutoUpdate(true);
    return blurView;
  }

  @Override
  @ReactProp(name = "type")
  public void setType(BlurView view, @Nullable String value) {
    view.setOverlayColor("dark".equals(value) ? darkOverlayColor : lightOverlayColor);
  }
}
