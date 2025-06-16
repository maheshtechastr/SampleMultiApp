package com.mpg.presentation.details

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mpg.models.product.Product
import com.mpg.presentation.Dimens
import com.mpg.presentation.Dimens.MediumPadding1
import com.mpg.presentation.R
import com.mpg.presentation.details.components.BottomShadow
import com.mpg.presentation.details.components.DetailsTopBar
import com.mpg.presentation.util.UIComponent
import com.mpg.theme.MultiAppTheme

@Composable
fun DetailsScreen(
    product: Product,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }

                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column {
            DetailsTopBar(
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, product.thumbnail)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBackClick = navigateUp
            )
            BottomShadow(alpha = 0.15f, height = Dimens.TopBarShadow)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding1),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context).data(product.thumbnail)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ProductImageHeight)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = product.title,
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(
                    id = R.color.text_title
                )
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.body
                )
            )
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))
            Text(
                text = "Price: \u20B9" + product.price,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.body
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    MultiAppTheme {
        DetailsScreen(
            product = Product(
                id = 1234,
                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                price = 0.0,
                thumbnail = "e-Gear-2BE6PRN.jpg"
            ),
            event = {},
            sideEffect = null
        ) {

        }
    }
}