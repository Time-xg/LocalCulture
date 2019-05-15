package top.timewl.localculture.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Polyline;
import com.tencent.mapsdk.raster.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import java.util.ArrayList;
import java.util.List;

import top.timewl.localculture.R;

public class GuideFragment extends BaseFragment {

    private View view;
    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.guide_layout,container,false);
        initView();
        mapView.onCreate(savedInstanceState);
        initMap();
        initPolyline();

        return view;
    }

    private void initPolyline() {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(24.65420372056891,116.96851730346678));

        Polyline polyline = mapView.addPolyline(new PolylineOptions().addAll(latLngs).
                color(0xff0066cc).
                width(10f)
                .arrowTexture(BitmapDescriptorFactory.fromResource(R.drawable.texture_arrow))
                .arrowGap(60));
        polyline.setWidth(16);
    }

    private void initMap() {
        TencentMap tencentMap = mapView.getMap();
        tencentMap.setCenter(new LatLng(24.6606683439,116.9784736633));
        tencentMap.setZoom(18);
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    private void initView() {
        mapView = view.findViewById(R.id.mapview);
    }
}
