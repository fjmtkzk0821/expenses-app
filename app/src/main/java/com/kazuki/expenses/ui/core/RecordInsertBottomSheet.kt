package com.kazuki.expenses.ui.core

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat.animate
import com.kazuki.expenses.ui.core.components.ChipGroup
import com.kazuki.expenses.utils.CommonTools
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecordInsertBottomSheet(state: BottomSheetState, viewModel: RecordInsertBottomSheetViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val transition = updateTransition(targetState = state)
//    val triggerBtnColor by transition.animateColor(label = "tbc") { state ->
//        if(state.isExpanded) {
//            MaterialTheme.colors.primary
//        } else {
//            Color.White
//        }
//    }
    val triggerBtnBackground by transition.animateColor(label = "tbbc") {
        if (it.isExpanded) {
            MaterialTheme.colors.background
//            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.colors.primary
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(0.85f)
//            .padding(12.dp)
    ) {
        Row() {
            Surface(modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        if (state.isExpanded)
                            state.collapse()
                        else
                            state.expand()
                    }
                }) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = if (state.isExpanded) "close" else "[add new record]",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.padding(8.dp)) {
                BasicTextField(
                    value = viewModel.name,
                    onValueChange = {
                        viewModel.name = it
                    },
                    textStyle = MaterialTheme.typography.h4,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation {
                        TransformedText(
                            AnnotatedString(it.text.ifEmpty { "[name]" }),
                            object : OffsetMapping {
                                override fun originalToTransformed(offset: Int): Int {
                                    return offset
                                }

                                override fun transformedToOriginal(offset: Int): Int {
                                    return offset
                                }

                            })
                    }
                )
            }
            Row(modifier = Modifier.padding(8.dp)) {
                BasicTextField(
                    value = viewModel.amount,
                    onValueChange = {
                        viewModel.amount = if (it.isNotEmpty()) it.filter { c -> c.isDigit() } else "0"
                    },
                    textStyle = MaterialTheme.typography.h4,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation {
                        TransformedText(
                            AnnotatedString(CommonTools.toCurrency(it.text)),
                            object : OffsetMapping {
                                override fun originalToTransformed(offset: Int): Int {
                                    return offset
                                }

                                override fun transformedToOriginal(offset: Int): Int {
                                    return offset
                                }

                            })
                    }
                )
            }
            ChipGroup(
                list = listOf("other", "food", "daily", "transport"),
                selected = viewModel.type,
                onChanged = {
                    viewModel.type = it
                })
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    viewModel.insertRecord()
                    coroutineScope.launch {
                        state.collapse()
                    }
                }) {
                Text("submit", modifier = Modifier.padding(vertical = 8.dp))
            }
            Spacer(modifier = Modifier.height(82.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BSPreview() {
    Surface() {
//        RecordInsertBottomSheet(
//            BottomSheetState(BottomSheetValue.Collapsed),
//            RecordInsertBottomSheetViewModel(context = LocalContext.current)
//        )
    }
}