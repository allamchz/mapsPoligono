package cr.ac.una.mapspoligono

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.maps.android.PolyUtil
import cr.ac.una.mapspoligono.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var polygon: Polygon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }
    private fun createPolygon(): Polygon {
        val polygonOptions = PolygonOptions()
        polygonOptions.add(LatLng(-14.0095923,108.8152324))
        polygonOptions.add(LatLng( -43.3897529,104.2449199))
        polygonOptions.add(LatLng( -51.8906238,145.7292949))
        polygonOptions.add(LatLng( -31.7289525,163.3074199))
        polygonOptions.add(LatLng( -7.4505398,156.2761699))
        polygonOptions.add(LatLng( -14.0095923,108.8152324))
        return mMap.addPolygon(polygonOptions)


    }
    private fun isLocationInsidePolygon(location: LatLng): Boolean {
        return polygon != null && PolyUtil.containsLocation(location, polygon?.points, true)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

        val sydney = LatLng(-14.0095923, 108.8152324)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        polygon = createPolygon()
        if (isLocationInsidePolygon(sydney)){
            println("+++++++++++++++++++++++++++++++++sidney esta en el mapa" )
        }

        val costaRica = LatLng(9.87466235556157, -83.97806864895828)
        if (!isLocationInsidePolygon(costaRica)){
            println("+++++++++++++++++++++++++++++++++CR no  esta en el mapa" )
        }


    }
}