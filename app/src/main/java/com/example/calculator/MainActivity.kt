package com.example.calculator


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var dotExist = false
    var lastNumberExist = false
    var sumOperatorCounter = 0
    var minusOperatorCounter = 0
    var divideOperatorCounter = 0
    var multiplicationOperatorCounter = 0
    var leftoverOperatorCounter = 0
    var percentOperatorCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view: View = binding.root
        setContentView(view)
    }
    fun setButtonClick(view : View){
        if(view is Button){
            binding.calculation.append(view.text)
            lastNumberExist = true
        }
    }
    fun clear(view: View){
        binding.calculation.text  = null
        Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
        lastNumberExist = false
        dotExist = false
         sumOperatorCounter = 0
         minusOperatorCounter = 0
         divideOperatorCounter = 0
         multiplicationOperatorCounter = 0
         leftoverOperatorCounter = 0
         percentOperatorCounter = 0
    }
    fun dot(view: View){
        view as Button
        if(lastNumberExist && !dotExist){
            binding.calculation.append(view.text)
            dotExist = true
            lastNumberExist = false
        }
    }
    fun backSpace(view: View){
        view as Button
        if( binding.calculation.text.isNotEmpty()) {
            var calculationTextView = binding.calculation.text
            if (calculationTextView.substring(calculationTextView.length - 1) == "."){
                var removedLastDotChar = calculationTextView.substring(0, binding.calculation.length() - 1)
                binding.calculation.text = removedLastDotChar
                lastNumberExist = true
                dotExist = false
            }else if (calculationTextView.substring(calculationTextView.length - 1) == binding.sum.text
                || calculationTextView.substring(calculationTextView.length - 1) == binding.divide.text
                || calculationTextView.substring(calculationTextView.length - 1) == binding.multiplication.text
                || calculationTextView.substring(calculationTextView.length - 1) == binding.minus.text
                || calculationTextView.substring(calculationTextView.length - 1) == binding.percent.text
                || calculationTextView.substring(calculationTextView.length - 1) == binding.leftOver.text){
                var removedLastOperator = calculationTextView.substring(0, binding.calculation.length() - 1)
                binding.calculation.text = removedLastOperator
                lastNumberExist = true
                sumOperatorCounter = 0
                minusOperatorCounter = 0
                divideOperatorCounter = 0
                multiplicationOperatorCounter = 0
                leftoverOperatorCounter = 0
                percentOperatorCounter = 0
            }
            else {
                var removedLastChar = calculationTextView.substring(0, binding.calculation.length() - 1)
                binding.calculation.text = removedLastChar
                lastNumberExist = false
                dotExist = false
            }

        }else if (binding.calculation.text.isEmpty()){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
        }
    }

    fun operators(view: View) {
        var button = view as Button

        if (button == binding.sum){

            if (lastNumberExist && sumOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = false
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }
            
        }else if (button == binding.minus){
            if (binding.calculation.text.isEmpty()){
                binding.calculation.append(button.text)
                lastNumberExist = false

            }
            if (lastNumberExist && minusOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = false
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }

        }else if (button == binding.multiplication){

            if (lastNumberExist && multiplicationOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = false
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }

        }else if (button == binding.divide){

            if (lastNumberExist && divideOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = false
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }
        }
        else if (button == binding.leftOver){
            if (lastNumberExist && leftoverOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = false
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }
        }
        else if(button == binding.percent){
            if (lastNumberExist && percentOperatorCounter == 0 ){
                binding.calculation.append(button.text)
                lastNumberExist = true
                dotExist = false
                sumOperatorCounter += 1
                minusOperatorCounter += 1
                divideOperatorCounter += 1
                multiplicationOperatorCounter += 1
                leftoverOperatorCounter += 1
                percentOperatorCounter += 1
            }
        }
    }
    
    fun equal(view: View) {

        var prefix = ""

        if (!lastNumberExist) return
        sumOperatorCounter = 0
        minusOperatorCounter = 0
        divideOperatorCounter = 0
        multiplicationOperatorCounter = 0
        leftoverOperatorCounter = 0
        percentOperatorCounter = 0

        if (binding.calculation.text.contains("+")) {
            val splitOperator = binding.calculation.text.split("+")
            var leftNumber = splitOperator[0]
            var rightNumber = splitOperator[1]
            if(leftNumber.contains(".") || rightNumber.contains(".") ) {
                var calculationResult = leftNumber.toDouble() + rightNumber.toDouble()

                var checkIntSplited = calculationResult.toString().split(".")
                var rightSplitedIntNumber = checkIntSplited[1]
                if (rightSplitedIntNumber.toInt() == 0){
                    var newResult =  calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                }else {
                    binding.calculation.text = calculationResult.toString()
                }
            }else {
                val calculationResult = leftNumber.toInt() + rightNumber.toInt()
                binding.calculation.text = calculationResult.toString()
            }
        }
        
        if (binding.calculation.text.contains("*")) {
            val splitOperator = binding.calculation.text.split("*")
            var leftNumber = splitOperator[0]
            var rightNumber = splitOperator[1]

            if(leftNumber.contains(".") || rightNumber.contains(".") ) {
                var calculationResult = leftNumber.toFloat() * rightNumber.toFloat()

                var checkIntSplited = calculationResult.toString().split(".")
                var rightSplitedIntNumber = checkIntSplited[1]
                if (rightSplitedIntNumber.toInt() == 0){
                    var newResult =  calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                }else {
                    binding.calculation.text = calculationResult.toString()
                }
            }else {
                val calculationResult = leftNumber.toInt() * rightNumber.toInt()
                binding.calculation.text = calculationResult.toString()
            }
        }

        if (binding.calculation.text.contains("/")) {
            val splitOperator = binding.calculation.text.split("/")
            var leftNumber = splitOperator[0]
            var rightNumber = splitOperator[1]

            if(leftNumber.contains(".") || rightNumber.contains(".")  ) {
                var calculationResult = leftNumber.toFloat() / rightNumber.toFloat()

                var checkIntSplited = calculationResult.toString().split(".")
                var rightSplitedIntNumber = checkIntSplited[1]
                if (rightSplitedIntNumber.toInt() == 0){
                    var newResult =  calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                }else {
                    binding.calculation.text = calculationResult.toString()
                }
            }else {
                val calculationResult = leftNumber.toFloat() / rightNumber.toFloat()
                var resultToString = calculationResult.toString().split(".")
                var rightNumber = resultToString[1]
                var leftNumber = resultToString[0]
                if(rightNumber.toInt() == 0){
                    var newResult = calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                } else binding.calculation.text = calculationResult.toString()
            }
        }

        if (binding.calculation.text.contains("%")){
            val splitOperator = binding.calculation.text.split("%")
            var leftNumber = splitOperator[0]
            var rightNumber = splitOperator[1]

            if(leftNumber.contains(".") || rightNumber.contains(".") ) {
                var calculationResult = leftNumber.toFloat() % rightNumber.toFloat()

                var checkIntSplited = calculationResult.toString().split(".")
                var rightSplitedIntNumber = checkIntSplited[1]
                if (rightSplitedIntNumber.toInt() == 0){
                    var newResult =  calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                }else {
                    binding.calculation.text = calculationResult.toString()
                }
            }else {
                val calculationResult = leftNumber.toInt() % rightNumber.toInt()
                binding.calculation.text = calculationResult.toString()
            }
        }
        if (binding.calculation.text.contains("@")){

            var splitOperator = binding.calculation.text.split("@")
            var leftNumber = splitOperator[0]
            var percentCalculation = leftNumber.toDouble() / 100.0
            var resultSplited = percentCalculation.toString().split(".")
            var rightSplitedNumber = resultSplited[1]
            if (rightSplitedNumber.toInt() == 0){
                var newResult =  percentCalculation.toInt()
                binding.calculation.text = newResult.toString()
            }else binding.calculation.text = percentCalculation.toString()

        }

        if (binding.calculation.text.contains("-")) {

            if (binding.calculation.text.startsWith(binding.minus.text)){
                prefix = binding.minus.text.toString()
                binding.calculation.text =  binding.calculation.text.toString().substring(1)
            }
            if (!binding.calculation.text.contains("-")) {
                binding.calculation.text  = prefix + binding.calculation.text
                return
            }

            var splitOperator = binding.calculation.text.split(binding.minus.text.toString())
            var leftNumber = splitOperator[0]
            var rightNumber = splitOperator[1]

            if (prefix.isNotEmpty()){
                leftNumber = prefix + leftNumber
            }
            if(leftNumber.contains(".") || rightNumber.contains(".") ) {
                var calculationResult = leftNumber.toFloat() - rightNumber.toFloat()

                var checkIntSplited = calculationResult.toString().split(".")
                var rightSplitedIntNumber = checkIntSplited[1]
                if (rightSplitedIntNumber.toInt() == 0){
                    var newResult =  calculationResult.toInt()
                    binding.calculation.text = newResult.toString()
                }else {
                    binding.calculation.text = calculationResult.toString()
                }
            }else {
                val calculationResult = leftNumber.toInt() - rightNumber.toInt()
                binding.calculation.text = calculationResult.toString()
            }
        }
        dotExist = dotStateAfterEqual(binding.calculation.text.toString())
    }
    fun dotStateAfterEqual(resultView : String) : Boolean{
        return resultView.contains(".")

    }
}