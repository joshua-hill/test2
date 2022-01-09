package com.example.tiptime
//eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: "xml file name in pascal case"Binding
    //This is used to interact with views in an xml file in the code
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{calculateTip()}
    }



    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if(cost == null){
            displayTip(0.0)
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.twenty_percent_option -> 0.2
            R.id.eighteen_percent_option -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)

        //------------
        val ageGroup = when(binding.tipOptions.checkedRadioButtonId){
            R.id.twenty_percent_option -> "0-5"
            R.id.eighteen_percent_option -> "5-10"
            R.id.fifteen_percent_option -> "10+"
            else -> "eee"
        }

        val weightGroup = when(binding.testOptions.checkedRadioButtonId){
            R.id.option_1 -> ">50"
            R.id.option_2 -> "50-100"
            R.id.option_3 -> "100-150"
            else -> "150+"
        }

        var user = Person(ageGroup, weightGroup)
    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_result, formattedTip)

    }
}
