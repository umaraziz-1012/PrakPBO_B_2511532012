package Pekan3;

import java.util.ArrayList;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	private ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal ,String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if(pinAwal.length() == 6) {
			this.pin = pinAwal;
		}else {
			System.out.print("Peringatan PIN Harus 6 digit!!, menggunakan pin default 123456");
			this.pin = "123456";
			
		}
		
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("rekening atas nama " + namaPemilik + " berhasil di buat " );
	}
	
	public String getnomorRekening() {return nomorRekening;}
	public String getnamaPemilik() {return namaPemilik;}
	
	public boolean Otentifikasi (String inputpin) {
		return this.pin.equals(inputpin);
	}
	
	
	//metod
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi TrxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(TrxBaru);
			
			System.out.println("Setor tunai Rp " + nominal + "Berhasil saldo saat ini : Rp " + saldo);
		}else {
			System.out.println("Gagal, Nominal setor harus lebih dari 0!");
		}
	}
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("---------------------");
	}
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Minimal saldo untuk di tarik adalah 10000");
		}else {
			if ((saldo-nominal)< 10000) {
				System.out.println("Gagal, Saldo Anda Sedikit");
			} else {
				saldo -= nominal;
				
				String idTrx = "TRX-T-" + System.currentTimeMillis();
				Transaksi TrxBaru = new Transaksi(idTrx, "Debit ", nominal);
				riwayatTransaksi.add(TrxBaru);
				
				System.out.println("Tarik tunai Rp " + nominal + "Berhasil, saldo saat ini : Rp " + saldo);
			}
		}
	}
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum Ada Transaksi Di Rekening ini");
		}else {
			for (Transaksi n : riwayatTransaksi) {
				n.cetakDetail();
			}
		}
	}
	
}

