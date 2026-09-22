package Pekan3;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama ");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Pilih Akun Aktif");
			System.out.println("3. Setor Tunai");
			System.out.println("4. Tarik Tunai");
			System.out.println("5. Cek Informasi Rekening");
			System.out.println("6. CetakMutasi");
			System.out.println("0. Keluar");
			System.out.print("pilih menu :  ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan nomor rekening : ");
				String no = input.nextLine();
				System.out.print("Masukkan nama pemilik : ");
				String nama = input.nextLine();
				System.out.print("Masukkan saldo awal : ");
				double saldo =  input.nextDouble();
				input.nextLine();
				System.out.print("Masukkan PIN Rekening Anda : ");
				String pinAwal = input.nextLine();
				
				Rekening rekeningBaru = new Rekening(no, nama, saldo, pinAwal);
				daftarRekening.add(rekeningBaru);
				akunAktif = rekeningBaru;
				break;
				
			case 3:	
				if (akunAktif == null) {
					System.out.println("Error :  Mohon Maaf, anda belum memiliki akun atau memilih rekkening!");
				} else {
						System.out.print("Masukkan Pin Anda : " );
						String pw = input.nextLine();
						
						if (akunAktif.Otentifikasi(pw)) {
						
								System.out.print("Pilih Jumlah Setoran : 	" );
								double setor = input.nextDouble();
								akunAktif.setorTunai(setor);
						}else {
						System.out.print("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;
			
			case 4:
					if (akunAktif == null) {
						System.out.println("Error :  Mohon Maaf, anda belum memiliki akun atau memilih rekkening!");
					} else {
						System.out.print("Masukkan nominal tarik : ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);
					}
					break;
				
			case 5:
				if (akunAktif == null) {
					System.out.println("Error, Anda Belum Membuka atau Memilih Rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 2:
				if (daftarRekening.isEmpty()) {
					System.out.print("Error, Anda Belum Membuka Rekening");
				}else {
					System.out.println("=== DAFTAR REKENING ===");

			        for (int i = 0; i < daftarRekening.size(); i++) {
			            System.out.println((i + 1) + ". Rekening " + (i + 1));
			        }

			        System.out.print("Pilih rekening: ");
			        int pilihanRekening = input.nextInt();

			        akunAktif = daftarRekening.get(pilihanRekening - 1);

			        System.out.println("Akun aktif berhasil diganti.");
			    }
			    break;
				
			case 6:
				if(akunAktif == null) {
					System.out.println("Error, Anda Belum Membuka atau Memilih Rekening!");
				}else {
					System.out.print("Masukkan PIN Anda : ");
					String pw = input.nextLine();
					
					if (akunAktif.Otentifikasi(pw)) {
						akunAktif.cetakMutasi();
					}else {
						System.out.print("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}

			case 0:
				isRunning = false;
				System.out.println("System di Tutup, Terima Kasih");
				break;
				
				default : System.out.println("Pilihan Tidak Valid");
			}
			
		}
		input.close();
	}

}
