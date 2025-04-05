package org.sleepapp.presentation.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.sleepapp.data.model.AlarmItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: (AlarmItem) -> Unit
) {
    var txt by remember {
        mutableStateOf("")
    }

  BasicAlertDialog(
        modifier = modifier.clip(RoundedCornerShape(20)),
        onDismissRequest = {onDismiss()}
    ){
      Surface {
          Column(
              modifier = modifier
                  .fillMaxWidth()
                  .padding(top = 30.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center
          ) {
              OutlinedTextField(
                  value = txt,
                  onValueChange = { newValue ->
                      txt = newValue.trimStart()
                  }
              )
              Spacer(modifier = Modifier.height(2.dp))
              TextButton(
                  onClick = {
                      if (txt.isNotEmpty()){
                          onConfirm(AlarmItem(
                              start = txt,
                              end = txt,
                              interval = txt,
                            )
                          )
                          onDismiss()
                      }
                  }
              ){
                  Text("Add Alarm")
              }
          }
      }
  }
}