package exam;

/**
 * The <code>Exam</code> object represents an exam written at the end of a semester.
 *
 * @author Lukas Mendel
 */

import java.time.LocalDate;
import java.util.Date;

public class Exam {

    private String subjectNumber;
    private String technicalName;
    private int semester;
    private LocalDate date;
    private String begin;
    private String duration;
    private String building;
    private String roomNumber;
    private int trialNumber;
    private double mark;
    private boolean insisted;
    private boolean currentExam;

    public Exam() {
    }

    public Exam(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public Exam(String subjectNumber, String technicalName, int semester, LocalDate date, String begin, String duration, String building, String roomNumber, int trialNumber, double mark, boolean insisted, boolean currentExam) {
        this.subjectNumber = subjectNumber;
        this.technicalName = technicalName;
        this.semester = semester;
        this.date = date;
        this.begin = begin;
        this.duration = duration;
        this.building = building;
        this.roomNumber = roomNumber;
        this.trialNumber = trialNumber;
        this.mark = mark;
        this.insisted = insisted;
        this.currentExam = currentExam;
    }

    public String getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(int trialNumber) {
        this.trialNumber = trialNumber;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public boolean isInsisted() {
        return insisted;
    }

    public void setInsisted(boolean insisted) {
        this.insisted = insisted;
    }

    public boolean isCurrentExam() {
        return currentExam;
    }

    public void setCurrentExam(boolean currentExam) {
        this.currentExam = currentExam;
    }
}
