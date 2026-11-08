
package shop.app;

import java.util.List;
import shop.StudentInfo;
import shop.model.*;
import shop.notification.*;
import shop.payment.*;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

public class TugasApp {

    public static void main(String[] args) {
        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo", 85000, 300);
        Product rendang = new PhysicalProduct("RDG-001", "Rendang Kemasan", 120000, 400);
        Product ebook = new DigitalProduct("EBK-001", "E-Book Resep Kue", 50000,"https://toko.example/ebook");
        List<Product> cart = List.of(coffee, rendang, ebook);

        CheckoutService checkout = new CheckoutService();
        ShippingService express = new ExpressShipping();
        EWalletPayment wallet =new EWalletPayment("DompetKu", 500000);
        PaymentMethod transfer =new BankTransferPayment("Bank Nagari");
        CreditCardPayment card =new CreditCardPayment("Siti Rahma", 1000000);

        System.out.println("===== T1 PEMBAYARAN =====");
        System.out.println(wallet.getName() + " : Rp"+ checkout.grandTotal(cart, express, wallet));
        System.out.println(transfer.getName() + " : Rp"+ checkout.grandTotal(cart, express, transfer));
        System.out.println(card.getName() + " : Rp"+ checkout.grandTotal(cart, express, card));

        System.out.println("\n===== POLICY =====");
        System.out.println("Kartu Kredit: " + card.policy());
        System.out.println("E-Wallet: " + wallet.policy());
        NotificationService service = new NotificationService();
        service.register(new EmailChannel());
        service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());

        service.register(new NotificationChannel() {

            @Override
            public String channelName() {
                return "Log";
            }

            @Override
            public boolean send(CustomerContact contact, String message) {
                System.out.println("[LOG] "
                        + contact.getName() + ": " + message);
                return true;
            }
        });

        String message = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. "
                + "Nomor resi akan muncul di aplikasi dalam 1x24 jam. "
                + "Terima kasih telah berbelanja produk UMKM Sumatera Barat "
                + "di toko kami. Kami berharap Anda puas dengan pelayanan "
                + "dan produk yang telah diterima.";

        CustomerContact siti = new CustomerContact("Siti Rahma","siti@example.com","081234567890");
        CustomerContact budi = new CustomerContact("Budi","","+6281298765432");

        System.out.println("\n===== NOTIFIKASI SITI =====");
        int result1 = service.broadcast(siti, message);
        System.out.println("Terkirim ke Siti lewat "+ result1 + " kanal");
        System.out.println();

        System.out.println("===== NOTIFIKASI BUDI =====");
        int result2 = service.broadcast(budi, message);
        System.out.println("Terkirim ke Budi lewat "+ result2 + " kanal");
    }
}
