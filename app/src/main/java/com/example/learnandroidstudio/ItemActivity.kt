package com.example.learnandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.yandex.pay.token.*

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val image: ImageView = findViewById(R.id.item_card_image)
        val title: TextView = findViewById(R.id.item_card_title)
        val text: TextView = findViewById(R.id.item_card_text)
        val price: TextView = findViewById(R.id.item_card_price)
        val yPayButton: YPayButton = findViewById(R.id.y_pay_button)

        val imageId = resources.getIdentifier(
            intent.getStringExtra("itemImage"),
            "drawable",
            this.packageName
        )

        image.setImageResource(imageId)
        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        price.text = intent.getStringExtra("itemPrice") + " $"

        val yPayConfig: YPayConfig = YPayConfig(
            merchantData = MerchantData(
                MerchantId("merchantId"),
                MerchantName("merchantName"),
                MerchantUrl("https://mySite.com")
            ),
            allowedAuthMethods = listOf(
                AuthMethod.PanOnly,
                AuthMethod.CloudToken
            ),
            allowedCardNetworks = listOf(CardNetwork.MIR, CardNetwork.VISA),
            environment = YPayApiEnvironment.SANDBOX
        )

        val paymentSession: PaymentSession = YPay.getPaymentSession(
            context = this,
            config = yPayConfig
        )
        paymentSession.bindTo(yPayButton)

         val yandexPayLauncher = registerForActivityResult(YPayContract()) { result: YPayResult ->
            when (result) {
                is YPayResult.Success -> {
                    val token = result.paymentToken
                    //send token in psp for continue payment
                    handleResult("Finished with success");
                }
                is YPayResult.Cancelled -> {
                    handleResult("Finished with cancelled event");
                }
                is YPayResult.Failure -> {
                    handleResult("Finished with domain error");
                }
            }
        }

        val tokenData = TokenData(
            order = Order(
                id = OrderId("orderId"),
                amount = Amount("100.00"),
                label = "label",
                items = listOf<OrderItem>(
                    OrderItem(
                        label = "product 1",
                        amount = Amount("100.00"),
                        type = OrderItemType.Pickup,
                        quantity = OrderItemQuantity(
                            count = 1,
                            label = "order item label"
                        )
                    )
                )
            ),
            paymentMethods = listOf<PaymentMethod>(
                PaymentMethod(
                    allowedAuthMethods = listOf(AuthMethod.PanOnly, AuthMethod.CloudToken),
                    type = PaymentMethodType.Card,
                    gateway = Gateway("gatewayName"),
                    allowedCardNetworks = CardNetwork.values().toList(),
                    gatewayMerchantId = GatewayMerchantId("MerchantGW1"),
                )
            ),
             RequiredFields(RequiredBillingContact(email = true)),
             CurrencyCode.RUB,
             CountryCode.RU
        )

        val contractParams = YPayContractParams(
            session = paymentSession,
            tokenData = tokenData
        )

        yandexPayLauncher.launch(contractParams)
    }

    private fun handleResult(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG)
    }
}