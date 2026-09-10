package Pekan1;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println("rekening atas nama " + namaPemilik + " berhasil di buat dengan saldo " + saldo);
	}

	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
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
				System.out.println("Tarik tunai Rp " + nominal + "Berhasil, saldo saat ini : Rp " + saldo);
			}
		}
	}

}
