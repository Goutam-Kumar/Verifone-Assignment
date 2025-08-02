package com.droidnext.countrysearch.presentation.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidnext.countrysearch.R
import com.droidnext.countrysearch.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen() {
    val context: Context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val viewModel: SearchViewModel = hiltViewModel()
    val filteredCountryList by viewModel.searchList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            label = { Text(context.getString(R.string.search_country)) },
            onValueChange = { newText ->
                text = newText
                viewModel.onQueryChanged(newText)
            },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if(text.isNotEmpty())
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.clear),
                        modifier = Modifier.clickable {
                            text = ""
                            viewModel.onQueryChanged("")
                        }
                    )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (filteredCountryList.isNotEmpty()) {
            LazyColumn {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column {
                            filteredCountryList.forEachIndexed { index, item ->
                                Box(
                                    Modifier
                                        .clickable { }
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(item.name)
                                }
                                if (index < filteredCountryList.lastIndex) {
                                    HorizontalDivider(
                                        modifier = Modifier.fillMaxWidth(),
                                        thickness = 1.dp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No Result found!")
            }
        }
    }
}