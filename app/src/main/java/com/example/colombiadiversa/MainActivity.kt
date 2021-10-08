package com.example.colombiadiversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
/*
    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {
        mPoi = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        mAdapter = Adapter(mPoi)
        recycler.adapter = mAdapter
    }

    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun generatePoi() {
        val poiString = readPoiJsonFile()
        try {
            val poiJson = JSONArray(poiString)
            for (i in 0 until poiJson.length()) {
                val poiJson = poiJson.getJSONObject(i)
                val poi = PoiItem(
                    poiJson.getString("name"),
                    poiJson.getString("shortDescription"),
                    poiJson.getString("review"),
                    poiJson.getString("image")
                )
                Log.d(TAG, "generateContacts: $poi")
                mPoi.add(poi)
            }
            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readPoiJsonFile(): String? {
        var poiString: String? = null
        try {
            val inputStream = assets.open("poi.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            poiString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return poiString
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }


 */
}