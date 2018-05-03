package main;

import config.Konfigurasi;
import dao.PersonDao;
import dao.StudentDao;
import dao.TransaksiDao;
import dao.TransaksiDetailDao;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Alamat;
import model.Kecamatan;
import model.Person;
import model.Student;

import model.Transaksi;
import model.TransaksiDetail;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApps {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //ctx.register(HelloWorldConfig.class);
        ctx.register(Konfigurasi.class);
        ctx.refresh();
        Scanner input = new Scanner(System.in);

        List<Student> listStudent = new LinkedList<>();
        List<Alamat> listAlamat = new LinkedList<>();
        List<Kecamatan> listKecamatan = new LinkedList<>();

        PersonDao personDao = ctx.getBean(PersonDao.class);
        StudentDao studentDao = ctx.getBean(StudentDao.class);

        while (true) {
            Kecamatan kecamatan = new Kecamatan();
            Alamat alamat = new Alamat();
            Student student = new Student();

            System.out.println("---- Menu ----");
            System.out.println("1. Insert Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("pilih no menu: ");
            String pilih1;
            pilih1 = input.next();
            int pilih;
            pilih = Integer.parseInt(pilih1);

            if (pilih == 1) {
                int menu;
                System.out.println("---- Menu ----");
                System.out.println("1. Insert Kecamatan saja");
                System.out.println("2. Insert Kecamatan dan Alamat");
                System.out.println("3. Insert Semua");
                System.out.println("pilih no menu: ");

                menu = input.nextInt();
                if (menu == 1) {
                    System.out.println("Masukkan Nama Kecamatan : ");
                    kecamatan.setNama(input.next());
                    studentDao.saveTransaksiKecamatan(kecamatan);

                }
                if (menu == 2) {
                    System.out.println("Masukkan Nama Kecamatan : ");
                    kecamatan.setNama(input.next());

                    System.out.println("Masukkan Nama Alamat : ");
                    alamat.setNama(input.next());
                    alamat.setKecamatan(kecamatan);
                    studentDao.saveTransaksiAlamat(alamat);
                }
                if (menu == 3) {
                    System.out.println("Masukkan Nama Kecamatan : ");
                    kecamatan.setNama(input.next());

                    System.out.println("Masukkan Nama Alamat : ");
                    alamat.setNama(input.next());
                    alamat.setKecamatan(kecamatan);

                    System.out.println("Masukkan Nama Student : ");
                    student.setNama(input.next());
                    student.setAlamat(alamat);
                }

            }
            if (pilih == 2) {
                int menu;
                System.out.println("---- Menu ----");
                System.out.println("1. Tampilkan Kecamatan saja");
                System.out.println("2. Tampilkan Alamat");
                System.out.println("3. Tampilkan Student");
                System.out.println("4. Tampilkan Semua");
                System.out.println("pilih no menu: ");

                menu = input.nextInt();
                if (menu == 1) {
                    listKecamatan = studentDao.findAllKecamatan();
                    for (Kecamatan kecamatan1 : listKecamatan) {
                        System.out.println("ID : " + kecamatan1.getId() + " Nama : " + kecamatan1.getNama());
                    }
                }
                if (menu == 2) {
                    listAlamat = studentDao.findAllAlamat();
                    for (Alamat alamat1 : listAlamat) {
                        System.out.println("ID : " + alamat1.getId() + " Nama Alamat: " + alamat1.getNama() + " idKecamatan: " + alamat1.getKecamatan().getId());
                    }
                }
                if (menu == 3) {
                    listStudent = studentDao.findAllStudent();
                    for (Student student1 : listStudent) {
                        System.out.println("ID : " + student1.getId() + " Nama Alamat: " + student1.getNama() + " idKecamatan: " + student1.getAlamat().getId());
                    }
                }
                if (menu == 4) {
                    System.out.println("Belum tersedia");
                }
            }
            if (pilih == 3) {
                int menu;
                System.out.println("---- Menu ----");
                System.out.println("1. update Kecamatan saja");
                System.out.println("2. update Alamat");
                System.out.println("3. update Semua");
                System.out.println("pilih no menu: ");

                menu = input.nextInt();
                if (menu == 1) {
                    listKecamatan = studentDao.findAllKecamatan();
                    for (Kecamatan kecamatan1 : listKecamatan) {
                        System.out.println("ID : " + kecamatan1.getId() + " Nama : " + kecamatan1.getNama());
                    }
                    System.out.println("Pilih ID yang ingin di update");
                    kecamatan.setId(input.nextInt());
                    System.out.println("Masukkan Nama Kecamatan : ");
                    kecamatan.setNama(input.next());
                    studentDao.updateTransaksiKecamatan(kecamatan);
                }
                if (menu == 2) {
                    listAlamat = studentDao.findAllAlamat();
                    for (Alamat alamat1 : listAlamat) {
                        System.out.println("ID : " + alamat1.getId() + " Nama Alamat: " + alamat1.getNama() + " idKecamatan: " + alamat1.getKecamatan().getId());
                    }
                    System.out.println("Pilih ID yang ingin di update");
                    alamat.setId(input.nextInt());
                    System.out.println("Masukkan Nama Alamat : ");
                    alamat.setNama(input.next());
                    listKecamatan = studentDao.findAllKecamatan();
                    for (Kecamatan kecamatan1 : listKecamatan) {
                        System.out.println("ID : " + kecamatan1.getId() + " Nama : " + kecamatan1.getNama());
                    }
                    System.out.println("Pilih ID yang ingin di update");
                    kecamatan.setId(input.nextInt());

                    System.out.println("Masukkan Id Kecamatan");
                    kecamatan.setId(input.nextInt());

                    System.out.println("Masukkan Nama Kecamatan");
                    kecamatan.setNama(input.next());

                    alamat.setKecamatan(kecamatan);
                    studentDao.updateTransaksiAlamat(alamat);

                }
                if (menu == 3) {
                    listStudent = studentDao.findAllStudent();
                    for (Student student1 : listStudent) {
                        System.out.println("ID : " + student1.getId() + " Nama Alamat: " + student1.getNama() + " idKecamatan: " + student1.getAlamat().getId());
                    }
                    System.out.println("Pilih ID Student yang ingin di Update");
                    student.setId(input.nextInt());
                    System.out.println("Masukkan Nama Student");
                    student.setNama(input.next());

                    listAlamat = studentDao.findAllAlamat();
                    for (Alamat alamat1 : listAlamat) {
                        System.out.println("ID : " + alamat1.getId() + " Nama Alamat: " + alamat1.getNama() + " idKecamatan: " + alamat1.getKecamatan().getId());
                    }
                    System.out.println("Pilih ID yang ingin di update");
                    alamat.setId(input.nextInt());
                    System.out.println("Masukkan Nama Alamat : ");
                    alamat.setNama(input.next());
                    listKecamatan = studentDao.findAllKecamatan();
                    for (Kecamatan kecamatan1 : listKecamatan) {
                        System.out.println("ID : " + kecamatan1.getId() + " Nama : " + kecamatan1.getNama());
                    }
                    System.out.println("Pilih ID yang ingin di update");
                    kecamatan.setId(input.nextInt());

                    System.out.println("Masukkan Id Kecamatan");
                    kecamatan.setId(input.nextInt());

                    System.out.println("Masukkan Nama Kecamatan");
                    kecamatan.setNama(input.next());

                    alamat.setKecamatan(kecamatan);
                    studentDao.updateTransaksiAlamat(alamat);
                }
            }
            if (pilih == 4) {
                int menu;
                System.out.println("---- Menu ----");
                System.out.println("1. Delete Kecamatan saja");
                System.out.println("2. Delete Alamat");
                System.out.println("3. Delete Student");
                System.out.println("pilih no menu: ");

                menu = input.nextInt();
                if (menu == 1) {
                    listKecamatan = studentDao.findAllKecamatan();
                    for (Kecamatan kecamatan1 : listKecamatan) {
                        System.out.println("ID : " + kecamatan1.getId() + " Nama : " + kecamatan1.getNama());
                    }
                    System.out.println("Masukkan ID yang ingin di hapus");
                    kecamatan.setId(input.nextInt());
                    kecamatan = studentDao.findKecamatanByID(kecamatan);
                    alamat = studentDao.findAlamatByIDKecamatan(kecamatan);

                    if (studentDao.findStudentByIDAlamat(alamat) != null) {
                        studentDao.deleteStudentByAlamat(alamat);
                    }
                    if (studentDao.findAlamatByIDKecamatan(kecamatan) != null) {
                        studentDao.deleteAlamat(alamat);
                    }

                    studentDao.deleteKecamatan(kecamatan);

                }
                if (menu == 2) {
                    listAlamat = studentDao.findAllAlamat();
                    for (Alamat alamat1 : listAlamat) {
                        System.out.println("ID : " + alamat1.getId() + " Nama Alamat: " + alamat1.getNama() + " idKecamatan: " + alamat1.getKecamatan().getId());
                    }
                    System.out.println("Masukkan ID yang ingin di hapus");
                    alamat.setId(input.nextInt());
                    alamat = studentDao.findAlamatByID(alamat);
                    if (studentDao.findStudentByIDAlamat(alamat) != null) {
                        studentDao.deleteStudentByAlamat(alamat);
                    }

                    studentDao.deleteAlamat(alamat);
                }
                if (menu == 3) {
                    listStudent = studentDao.findAllStudent();
                    for (Student student1 : listStudent) {
                        System.out.println("ID : " + student1.getId() + " Nama Alamat: " + student1.getNama() + " idKecamatan: " + student1.getAlamat().getId());
                    }
                    System.out.println("pilih ID yang ingin dihapus");
                    student.setId(input.nextInt());
                    studentDao.deleteStudent(student);
                }
            }

            System.out.println("Lagi? (true/false)");
            Boolean pilihan;
            pilihan = input.nextBoolean();
            if (pilihan == false) {
                break;
            }

        }

//        Alamat alamat = new Alamat();
//        alamat.setJalan("Baleendah");
//        alamat.setKodePos("40375");
//        alamat.setKota("Bandung");
//        
//        
//        Person person = new Person();
//        person.setAge(22);
//        person.setNamaBelakang("Simamora");
//        person.setNamaDepan("Victor");
//        person.setAlamat(alamat);
//        
//        personDao.saveTransaksiHibernate(person);
//        while (true) {
//            int pilihan;
//
//            System.out.println("1. Insert Data Transaksi ");
//            System.out.println("2. Tampilkan Data Transaksi ");
//            System.out.println("3. Update Data Transaksi ");
//            System.out.println("4. Delete Data Transaksi ");
//            System.out.println("5. Insert Data Transaksi Detil ");
//            System.out.println("6. Tampilkan Data Transaksi Detil");
//            System.out.println("7. Update Data Transaksi Detil");
//            System.out.println("8. Delete Data Transaksi Detil");
//            System.out.println("Masukkan Pilihan anda : ");
//            
//            pilihan = input.nextInt();
//            Timestamp time = new Timestamp(System.currentTimeMillis());
//            System.out.println(time);
//            TransaksiDao transaksiDao = ctx.getBean(TransaksiDao.class);
//            TransaksiDetailDao transaksiDetailDao = ctx.getBean(TransaksiDetailDao.class);
//            List<Transaksi> listTransaksi = new LinkedList<>();
//            List<TransaksiDetail> listTransaksiDetail = new LinkedList<>();
//
//            Transaksi transaksi = new Transaksi();
//            TransaksiDetail transaksiDetail = new TransaksiDetail();
//
////          
//            transaksi.setWaktu(new Timestamp(System.currentTimeMillis()));
//            transaksiDao.saveTransaksiHibernate(transaksi);
//            listTransaksi = transaksiDao.findAllHibernate();
//            for (Transaksi transaksi1 : listTransaksi) {
//                System.out.println(transaksi1.getId()+" "+transaksi1.getWaktu());
//            }
//            if (pilihan == 1) {
//                transaksiDao.addTransaksi(time);
//            }
//            if (pilihan == 2) {
//                System.out.println("1.Tampilkan Semua");
//                System.out.println("2.Tampilkan By ID");
//                pilihan = input.nextInt();
//                if (pilihan == 1) {
//                    listTransaksi = transaksiDao.findAll();
//                    for (Transaksi transaksi1 : listTransaksi) {
//                        System.out.println("ID : " + transaksi1.getId() + " Waktu : " + transaksi1.getWaktu());
//                    }
//                } else {
//                    System.out.println("Masukkan ID");
//
//                    transaksi = transaksiDao.findById(input.nextInt());
//                    System.out.println("ID : " + transaksi.getId() + " Waktu : " + transaksi.getWaktu());
//                }
//            }
//            if (pilihan == 3) {
//                listTransaksi = transaksiDao.findAll();
//                for (Transaksi transaksi1 : listTransaksi) {
//                    System.out.println("ID : " + transaksi1.getId() + " Waktu : " + transaksi1.getWaktu());
//                }
//                System.out.println("Masukkan ID yang ingin di update : ");
//                transaksi.setId(input.nextInt());
//                transaksi.setWaktu(time);
//                transaksiDao.updateTransaksi(transaksi);
//            }
//            if (pilihan == 4) {
//                listTransaksi = transaksiDao.findAll();
//                for (Transaksi transaksi1 : listTransaksi) {
//                    System.out.println("ID : " + transaksi1.getId() + " Waktu : " + transaksi1.getWaktu());
//                }
//                System.out.println("Masukkan ID Transaksi yang ingin dihapus");
//                transaksi.setId(input.nextInt());
//                List<Map<String, Object>> listMap = transaksiDao.findAllJoin(transaksi.getId());
//                if (listMap.isEmpty()) {
//                    transaksiDao.deleteTransaksi(transaksi);
//                } else {
//                    System.out.println("Transaksi ini masih terhubung dengan transaksi detail, hapus terlebih dahulu transaksi detail");
//                    for (Map<String, Object> map : listMap) {
//                        System.out.println(Arrays.asList(map));
//                    }
//                }
//
//            }
//            if (pilihan == 5) {
//                System.out.println("Masukkan Data Transaksi Detail : ");
//                listTransaksi = transaksiDao.findAll();
//                for (Transaksi transaksi1 : listTransaksi) {
//                    System.out.println("Pilihan ID : " + transaksi1.getId() + " Waktu : " + transaksi1.getWaktu());
//                }
//                System.out.println("Pilih Id Transaksi");
//                transaksiDetail.setIdTransaksi(input.nextInt());
//                System.out.println("Masukkan nama Item");
//                transaksiDetail.setItem(input.next());
//                System.out.println("Masukkan Jumlah item");
//                transaksiDetail.setQty(input.nextInt());
//                System.out.println("Masukkan Harga per Item");
//                transaksiDetail.setPrice(input.nextBigDecimal());
//                transaksiDetailDao.addTransaksiDao(transaksiDetail);
//            }
//            if (pilihan == 6) {
//                System.out.println("1.Tampilkan Semua");
//                System.out.println("2.Tampilkan By ID");
//                pilihan = input.nextInt();
//                if (pilihan == 1) {
//                    listTransaksiDetail = transaksiDetailDao.findAll();
//                    for (TransaksiDetail transaksiDetail1 : listTransaksiDetail) {
//                        System.out.println("ID : " + transaksiDetail1.getId() + " ID Transaksi: " + transaksiDetail1.getIdTransaksi()
//                                + " Item : " + transaksiDetail1.getItem() + " QTY : " + transaksiDetail1.getQty() + " Price : " + transaksiDetail1.getPrice());
//                    }
//                } else {
//                    System.out.println("Masukkan ID");
//                    transaksiDetail = transaksiDetailDao.findById(input.nextInt());
//                    System.out.println("ID : " + transaksiDetail.getId() + " ID Transaksi: " + transaksiDetail.getIdTransaksi()
//                            + " Item : " + transaksiDetail.getItem() + " QTY : " + transaksiDetail.getQty() + " Price : " + transaksiDetail.getPrice());
//                }
//
//            }
//            if (pilihan == 7) {
//                listTransaksiDetail = transaksiDetailDao.findAll();
//                for (TransaksiDetail transaksiDetail1 : listTransaksiDetail) {
//                    System.out.println("ID : " + transaksiDetail1.getId() + " ID Transaksi: " + transaksiDetail1.getIdTransaksi()
//                            + " Item : " + transaksiDetail1.getItem() + " QTY : " + transaksiDetail1.getQty() + " Price : " + transaksiDetail1.getPrice());
//                }
//                System.out.println("Masukkan ID yang ingin di update : ");
//                transaksiDetail.setId(input.nextInt());
//                System.out.println("Masukkan Data Transaksi Detail : ");
//                listTransaksi = transaksiDao.findAll();
//                for (Transaksi transaksi1 : listTransaksi) {
//                    System.out.println("Pilihan ID : " + transaksi1.getId() + " Waktu : " + transaksi1.getWaktu());
//                }
//                System.out.println("Pilih Id Transaksi");
//                transaksiDetail.setIdTransaksi(input.nextInt());
//                System.out.println("Masukkan nama Item");
//                transaksiDetail.setItem(input.next());
//                System.out.println("Masukkan Jumlah item");
//                transaksiDetail.setQty(input.nextInt());
//                System.out.println("Masukkan Harga per Item");
//                transaksiDetail.setPrice(input.nextBigDecimal());
//                transaksiDetailDao.updateTransaksi(transaksiDetail);
//
//            }
//            if (pilihan == 8) {
//                listTransaksiDetail = transaksiDetailDao.findAll();
//                for (TransaksiDetail transaksiDetail1 : listTransaksiDetail) {
//                    System.out.println("ID : " + transaksiDetail1.getId() + " ID Transaksi: " + transaksiDetail1.getIdTransaksi()
//                            + " Item : " + transaksiDetail1.getItem() + " QTY : " + transaksiDetail.getQty() + " Price : " + transaksiDetail1.getPrice());
//                }
//                System.out.println("Masukkan ID yang ingin dihapus : ");
//                transaksiDetailDao.deleteTransaksi(input.nextInt());
//
//            }
//            System.out.println("Lagi?  (true/false)");
//            Boolean lagi = input.nextBoolean();
//            if (lagi == false) {
//                break;
//            }
//        }
    }

}
