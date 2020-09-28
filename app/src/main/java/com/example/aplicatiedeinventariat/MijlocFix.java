package com.example.aplicatiedeinventariat;

import android.os.Parcel;
import android.os.Parcelable;

public class MijlocFix implements Parcelable {

    private String nume;
    private String categorie;
    private int cantitate;
    private String furnizor;
    private float pret;
    private String dataAdaugare;

    public MijlocFix(){

    }

    public MijlocFix(String nume,String categorie,int cantitate,String furnizor,float pret,String dataAdaugare) {
    this.nume=nume;
    this.categorie=categorie;
    this.cantitate=cantitate;
    this.furnizor=furnizor;
    this.pret=pret;
    this.dataAdaugare=dataAdaugare;
    }


  protected MijlocFix(Parcel in)
  {
      nume=in.readString();
      categorie=in.readString();
      cantitate=in.readInt();
      furnizor=in.readString();
      pret=in.readFloat();
      dataAdaugare=in.readString();
  }
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(String furnizor) {
        this.furnizor = furnizor;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    public String getDataAdaugare() {
        return dataAdaugare;
    }

    public void setDataAdaugare(String dataAdaugare) {
        this.dataAdaugare = dataAdaugare;
    }

  public static final Creator<MijlocFix> CREATOR=new Creator<MijlocFix>() {
      @Override
      public MijlocFix createFromParcel(Parcel in) {
          return new MijlocFix(in);
      }

      @Override
      public MijlocFix[] newArray(int size) {
          return new MijlocFix[size];
      }
  };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
      parcel.writeString(nume);
      parcel.writeString(categorie);
      parcel.writeInt(cantitate);
      parcel.writeString(furnizor);
      parcel.writeFloat(pret);
      parcel.writeString(dataAdaugare);
    }

    @Override
    public String toString() {
        return "MijlocFix{" +
                "nume=" + nume +
                ",categorie='" + categorie  +
                ",cantitate=" + cantitate +
                ",furnizor=" + furnizor +
                ",pret=" + pret +
                ",dataAdaugare='" + dataAdaugare  +
                '}';
    }


}
