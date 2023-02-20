package com.example.garage

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.garage.part.Part
import com.example.garage.part.PartAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    var brand: String = ""
    var model: String = ""
    var parts = ArrayList<Part>()
    var partsList: ListView? = null
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val autoBrandSpinner = findViewById<Spinner>(R.id.auto_brand)
        val autoBrandAdapter = ArrayAdapter.createFromResource(
            this, R.array.brands, android.R.layout.simple_spinner_item)

        autoBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autoBrandSpinner.adapter = autoBrandAdapter

        autoBrandSpinner.setSelection(0)

        autoBrandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long ) {
                val brandChoose = resources.getStringArray(R.array.brands)
                brand = brandChoose[selectedItemPosition]

                val autoModelSpinner = findViewById<Spinner>(R.id.auto_model)
                val autoModelAdapter = ArrayAdapter.createFromResource(
                    this@MainActivity,
                    arrayOfModels(brand),
                    android.R.layout.simple_spinner_item)

                autoModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                autoModelSpinner.adapter = autoModelAdapter

                autoModelSpinner.setSelection(0)

                autoModelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        itemSelected: View, selectedItemPosition: Int, selectedId: Long ) {
                        val modelChoose = resources.getStringArray(arrayOfModels(brand))
                        model = modelChoose[selectedItemPosition]
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) { }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        setInitialData()
        partsList = findViewById(R.id.parts_list)
        val partAdapter = PartAdapter(this, R.layout.spare_parts_list, parts)

        partsList?.adapter = partAdapter

        val itemListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val selectedPart = parent.getItemAtPosition(position) as Part
                selectedPart.setChoose(!selectedPart.getChoose())
                partAdapter.notifyDataSetChanged()
            }
        partsList?.onItemClickListener = itemListener

        val phoneField = findViewById<TextInputEditText>(R.id.customer_phone)
        phoneField.addTextChangedListener(PhoneNumberFormattingTextWatcher("UA"))

        val orderButton = findViewById<MaterialButton>(R.id.order_button)
        orderButton.setOnClickListener {
            val name: String = findViewById<TextInputEditText>(R.id.customer_name).text.toString()
            val phone = phoneField.text.toString()
            if (customerNameValidation(name)) {
                Toast.makeText(
                    applicationContext, "Customer's name field has to be filled!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (customerPhoneValidation(phone)) {
                Toast.makeText(
                    applicationContext, "Customer's phone field has to be filled!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (carModelValidation()) {
                Toast.makeText(
                    applicationContext, "You have to choose any one car model!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (!sparePartsChooseValidation()) {
                Toast.makeText(
                    applicationContext, "You have to choose at least one spare part to place order!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            Log.d(tag, "Customer's name: $name")
            Log.d(tag, "Customer's phone: $phone")
            Log.d(tag, "Car brand: $brand")
            Log.d(tag, "Car model: $model")
            Log.d(tag, "List of ordered spare parts:")
            for (index in parts.indices) {
                if (parts[index].getChoose()) {
                    Log.d(tag, parts[index].getName())
                }
            }
        }
    }

    private fun arrayOfModels(brand: String) :Int {
        return when (brand) {
            "Ford" -> R.array.ford_models
            "Mercedes" -> R.array.mercedes_models
            "Opel" -> R.array.opel_models
            "VW" -> R.array.vw_models
            else -> R.array.no_models
        }
    }

    private fun setInitialData() {
        parts.add(Part("Engine",
            "4D56U D-ID DOHC 16V 2.5 Turbo Diesel Engine 2006-2014",
            R.drawable.engine_64))
        parts.add(Part("Transmission",
            "AE8Z-7Z369-F Transmission Computer TCM Fit 2011-2018",
            R.drawable.transmission_64))
        parts.add(Part("Turbine",
            "F10 550i turbocharger 7605794 4.40 330kw 2015",
            R.drawable.turbine_64))
        parts.add(Part("Suspension",
            "Complete Air Ride Suspension Kit 3/8\" Air Manifold Bags & Tank",
            R.drawable.suspension_64))
        parts.add(Part("Exhaust system",
            "Complete EXHAUST PIPE & MUFFLER SET",
            R.drawable.exhaust_64))
        parts.add(Part("Car headlight",
            "Xenon BiXenon Complete Head Light With Bulbs Both Front Side RHD CAR",
            R.drawable.car_light_64))
        parts.add(Part("Engine Oil",
            "20L Fanfaro 5W-30 C3 Fully Synthetic Engine Oil Longlife 3 504 00/50",
            R.drawable.engine_oil_64))
    }

    private fun customerNameValidation(name: String?) :Boolean {
        if (name.isNullOrBlank()) {
            return true
        }
        val names = name.split(' ')
        return !(names.size == 2 && names.all { it.isNotBlank() && it[0].isUpperCase() })
    }

    private fun customerPhoneValidation(phone: String?): Boolean {
        if (phone.isNullOrBlank()) {
            return true
        }
        if (phone.length != 16) {
            return true
        }
        if (phone.substring(0, 4) != "+380") {
            return true
        }
        return false
    }

    private fun carModelValidation() :Boolean {
        return this.model == "" || this.model == "Nothing selected"
    }

    private fun sparePartsChooseValidation() :Boolean {
        for (i in parts.indices) {
            if (parts[i].getChoose()) {
                return true
            }
        }
        return false
    }

}