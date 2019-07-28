package com.f4str.conversationstartergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.io.IOException

class MainActivity : AppCompatActivity() {
	var starters = mutableListOf<String>()
	var pointer: Int = 0
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		starters = readFile("starters.txt").toMutableList()
		starters.shuffle()
		
		generate(null)
	}
	
	fun readFile(fileName: String): List<String> {
		return try {
			application.assets.open(fileName).bufferedReader().readLines()
		}
		catch (e: IOException) {
			listOf("FILE NOT FOUND")
		}
	}
	
	fun generate(@Suppress("UNUSED_PARAMETER") view: View?) {
		if (pointer >= starters.size) {
			starters.shuffle()
			pointer = 0
		}

		val starter = starters[pointer]
		pointer++
		
		val box = findViewById<TextView>(R.id.mainText)
		box.text = starter
	}
}
