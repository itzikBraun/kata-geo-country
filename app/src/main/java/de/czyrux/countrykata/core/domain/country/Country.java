package de.czyrux.countrykata.core.domain.country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("altSpellings")
    private String[] alternativeSpellings;
    @SerializedName("relevance")
    private float relevance;
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subregion;

    @SerializedName("population")
    private long population;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("area")
    private float area;

    @SerializedName("gini")
    private float gini;

    @SerializedName("timezones")
    private String[] timezones;

    @SerializedName("borders")
    private String[] borders;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("callingCodes")
    private String[] callingCodes;

    @SerializedName("topLevelDomain")
    private String[] topLevelDomain;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("currencies")
    private String[] currencies;
    @SerializedName("languages")
    private String[] languages;

    @SerializedName("latlng")
    private float[] latlong;

    @SerializedName("translations")
    private Map<String, String> translations;

    public Country() { }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(final String capital) {
        this.capital = capital;
    }

    public float getRelevance() {
        return relevance;
    }

    public void setRelevance(final float relevance) {
        this.relevance = relevance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(final long population) {
        this.population = population;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(final String subregion) {
        this.subregion = subregion;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(final String demonym) {
        this.demonym = demonym;
    }

    public float getArea() {
        return area;
    }

    public void setArea(final float area) {
        this.area = area;
    }

    public float getGini() {
        return gini;
    }

    public void setGini(final float gini) {
        this.gini = gini;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(final String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(final String[] borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(final String nativeName) {
        this.nativeName = nativeName;
    }

    public String[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(final String[] callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(final String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(final String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(final String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(final String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(final String[] languages) {
        this.languages = languages;
    }

    public String[] getAlternativeSpellings() {
        return alternativeSpellings;
    }

    public void setAlternativeSpellings(final String[] alternativeSpellings) {
        this.alternativeSpellings = alternativeSpellings;
    }

    public float[] getLatlong() {
        return latlong;
    }

    public void setLatlong(final float[] latlong) {
        this.latlong = latlong;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(final Map<String, String> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("name='").append(name).append('\'');
        sb.append(", capital='").append(capital).append('\'');
        sb.append(", alternativeSpellings=").append(Arrays.toString(alternativeSpellings));
        sb.append(", relevance=").append(relevance);
        sb.append(", region='").append(region).append('\'');
        sb.append(", subregion='").append(subregion).append('\'');
        sb.append(", population=").append(population);
        sb.append(", demonym='").append(demonym).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", gini=").append(gini);
        sb.append(", timezones=").append(Arrays.toString(timezones));
        sb.append(", borders=").append(Arrays.toString(borders));
        sb.append(", nativeName='").append(nativeName).append('\'');
        sb.append(", callingCodes=").append(Arrays.toString(callingCodes));
        sb.append(", topLevelDomain=").append(Arrays.toString(topLevelDomain));
        sb.append(", alpha2Code='").append(alpha2Code).append('\'');
        sb.append(", alpha3Code='").append(alpha3Code).append('\'');
        sb.append(", currencies=").append(Arrays.toString(currencies));
        sb.append(", languages=").append(Arrays.toString(languages));
        sb.append(", latlong=").append(Arrays.toString(latlong));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.name);
        dest.writeString(this.capital);
        dest.writeStringArray(this.alternativeSpellings);
        dest.writeFloat(this.relevance);
        dest.writeString(this.region);
        dest.writeString(this.subregion);
        dest.writeLong(this.population);
        dest.writeString(this.demonym);
        dest.writeFloat(this.area);
        dest.writeFloat(this.gini);
        dest.writeStringArray(this.timezones);
        dest.writeStringArray(this.borders);
        dest.writeString(this.nativeName);
        dest.writeStringArray(this.callingCodes);
        dest.writeStringArray(this.topLevelDomain);
        dest.writeString(this.alpha2Code);
        dest.writeString(this.alpha3Code);
        dest.writeStringArray(this.currencies);
        dest.writeStringArray(this.languages);
        dest.writeFloatArray(this.latlong);
        if (translations != null) {
            ArrayList<String> translationsKeys = new ArrayList<>();
            translationsKeys.addAll(this.translations.keySet());

            ArrayList<String> translationsValues = new ArrayList<>();
            translationsValues.addAll(this.translations.values());
            dest.writeList(translationsKeys);
            dest.writeList(translationsValues);
        }
    }

    protected Country(final Parcel in) {
        this.name = in.readString();
        this.capital = in.readString();
        this.alternativeSpellings = in.createStringArray();
        this.relevance = in.readFloat();
        this.region = in.readString();
        this.subregion = in.readString();
        this.population = in.readLong();
        this.demonym = in.readString();
        this.area = in.readFloat();
        this.gini = in.readFloat();
        this.timezones = in.createStringArray();
        this.borders = in.createStringArray();
        this.nativeName = in.readString();
        this.callingCodes = in.createStringArray();
        this.topLevelDomain = in.createStringArray();
        this.alpha2Code = in.readString();
        this.alpha3Code = in.readString();
        this.currencies = in.createStringArray();
        this.languages = in.createStringArray();
        this.latlong = in.createFloatArray();

        ArrayList<String> translationsKeys;
        ArrayList<String> translationsValues;
        translationsKeys = new ArrayList<>();
        in.readList(translationsKeys, String.class.getClassLoader());
        translationsValues = new ArrayList<>();
        in.readList(translationsValues, String.class.getClassLoader());
        translations = new HashMap<>();
        for (int i = 0; i < translationsKeys.size(); i++) {
            translations.put(translationsKeys.get(i), translationsValues.get(i));
        }
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(final Parcel source) {
            return new Country(source);
        }

        public Country[] newArray(final int size) {
            return new Country[size];
        }
    };
}
