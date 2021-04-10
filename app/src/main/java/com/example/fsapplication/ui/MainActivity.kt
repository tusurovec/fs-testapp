package com.example.fsapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fsapplication.R
import com.example.fsapplication.domain.entity.Currency
import com.example.fsapplication.presentation.CurrenciesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: CurrenciesViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(CurrenciesViewModel::class.java)

		viewModel.currencies.observe(this, Observer<List<Currency>> { currencies ->
			currenciesList.adapter = CurrencyAdapter(currencies)
			progress.isVisible = false
		})

		viewModel.loadCurrencies()
	}
}