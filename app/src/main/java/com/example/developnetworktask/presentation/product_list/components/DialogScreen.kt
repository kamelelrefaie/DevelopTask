import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.developnetworktask.domain.model.ProductItem
import com.example.developnetworktask.presentation.product_list.components.DialogProduct
import com.example.developnetworktask.presentation.product_list.components.ProductItemComposable


@Composable
fun CustomDialog(productItem: ProductItem, setShowDialog: (Boolean) -> Unit) {



    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier  = Modifier.height(600.dp)
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(Modifier.fillMaxSize()) {

                       DialogProduct(productItem = productItem)

//                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                        Button(
//                            onClick = {
//                                setShowDialog(false)
//                            },
//                            shape = RoundedCornerShape(50.dp),
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(50.dp)
//                        ) {
//                            Text(text = "Done")
//                        }
//                    }
                }
            }
        }
    }
}