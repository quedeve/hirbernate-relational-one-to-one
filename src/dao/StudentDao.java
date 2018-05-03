/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.Student;
import model.Kecamatan;
import model.Alamat;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author victo
 */
@Repository
@Transactional(readOnly = false)
public class StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    //hibernate
    @Transactional(readOnly = true)
    public List<Student> findAllStudent() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Student"); // dari entity bukan dari table
        return query.list();
    }

    public List<Alamat> findAllAlamat() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Alamat"); // dari entity bukan dari table
        return query.list();
    }

    public List<Kecamatan> findAllKecamatan() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Kecamatan"); // dari entity bukan dari table
        return query.list();
    }

    public List<Map<String, Object>> findAllJoin() {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT s.*, a.*, k.* FROM td_Student s JOIN "
                + "td_Alamat a ON s.ID_ALAMAT_FK = a.ID JOIN td_Kecamata k ON a.ID_KECAMATAN_FK = k.ID ");
        return query.list();
    }

    public Alamat findAlamatByID(Alamat alamat) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ALAMAT where ID = :id").setParameter("id", alamat.getId());
        return (Alamat) query.list().get(0);
    }

    public Kecamatan findKecamatanByID(Kecamatan kecamatan) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Kecamatan where ID = :id").setParameter("id", kecamatan.getId());
        return (Kecamatan) query.list().get(0);
    }

    public Alamat findAlamatByIDKecamatan(Kecamatan alamat) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ALAMAT where ID_KECAMATAN_FK = :id").setParameter("id", alamat.getId());
        return (Alamat) query.list().get(0);
    }

    public Student findStudentByIDAlamat(Alamat alamat) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ALAMAT where ID_ALAMAT_FK = :id").setParameter("id", alamat.getId());
        return (Student) query.list().get(0);
    }

    public void saveTransaksiStudent(Student student) {

        sessionFactory.getCurrentSession().save(student);
    }

    public void saveTransaksiAlamat(Alamat alamat) {

        sessionFactory.getCurrentSession().save(alamat);
    }

    public void saveTransaksiKecamatan(Kecamatan kecamatan) {
        sessionFactory.getCurrentSession().save(kecamatan);
    }

    public void updateTransaksiStudent(Student student) {

        sessionFactory.getCurrentSession().update(student);
    }

    public void updateTransaksiAlamat(Alamat alamat) {

        sessionFactory.getCurrentSession().update(alamat);
    }

    public void updateTransaksiKecamatan(Kecamatan kecamatan) {
        sessionFactory.getCurrentSession().update(kecamatan);
    }

    public void deleteKecamatan(Kecamatan kecamatan) {
        sessionFactory.getCurrentSession().delete(kecamatan);
    }

    public void deleteAlamat(Alamat alamat) {
        sessionFactory.getCurrentSession().delete(alamat);
    }

    public void deleteStudent(Student student) {
        sessionFactory.getCurrentSession().delete(student);
    }

    public void deleteStudentByAlamat(Alamat alamat) {

        sessionFactory.getCurrentSession().createQuery("DELETE Student WHERE ID_ALAMAT_FK = :alamat ").setParameter("alamat", alamat.getId());
    }

    public void deleteAlamatByKecamatan(Kecamatan alamat) {

        sessionFactory.getCurrentSession().createQuery("DELETE Student WHERE ID_ALAMAT_FK = :alamat ").setParameter("alamat", alamat.getId());
    }

}
