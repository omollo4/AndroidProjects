package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange
import com.example.calculator.ui.theme.MediumGray as MediumGray

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                        .background(LightGray)
                ) {
                    onAction(CalculatorAction.Clear)
                }
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(LightGray)
                ) {
                    onAction(CalculatorAction.Delete)
                }
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(Orange)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(7))
                }
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(8))
                }
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(9))
                }
                CalculatorButton(
                    symbol = "x",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(Orange)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(4))
                }
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(5))
                }
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(6))
                }
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(Orange)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(1))
                }
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(2))
                }
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(3))
                }
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(Orange)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Number(0))
                }
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(MediumGray)
                ) {
                    onAction(CalculatorAction.Decimal)
                }
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .background(Orange)
                ) {
                    onAction(CalculatorAction.Result)
                }
            }
        }
    }
}

