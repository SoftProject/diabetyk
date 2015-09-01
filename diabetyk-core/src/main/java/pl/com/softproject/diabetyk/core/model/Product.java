package pl.com.softproject.diabetyk.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

/**
 * Class Product
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Entity
@Table(name = "product", schema = "public")
@SuppressWarnings("PersistenceUnitPresent")
public class Product extends BaseEntity {

    @Column(length = 256)
    @Size(min = 3, max = 256)
    private String name;

    @Column(length = 256)
    @Size(min = 2, max = 256)
    private String description;

    @DecimalMin("0")
    private Double fat;

    @DecimalMin("0")
    private Double protein;

    @DecimalMin("0")
    private Double carbohydrates;

    /**
     * waga na jeden wymiennik węglowodanowy
     */
    private Double weightForOneWw;

    /**
     * miara domowa
     */
    @Column(length = 256)
    private String homeMeasure;

    /**
     * ilość wymienników węglowodanowych w porcji
     */
    @DecimalMin("0")
    private Integer wwInPortion;

    @ManyToOne
    @JoinColumn(name = "userdata_id")
    @JsonIgnore
    private UserData author;

    @Column(name = "is_moderated", nullable = false)
    private boolean moderated = false;

    @Column(name = "product_normalized_name", unique = true)
    private String productNormalizedName;

    @Column(name = "is_allergen", nullable = false)
    private boolean allergen = false;

    @Column(name = "is_glutenFree", nullable = false)
    private boolean glutenFree = false;

    @DecimalMin("0")
    private Double saturatedFattyAcids;

    @DecimalMin("0")
    private Double oneSaturatedFattyAcids;

    @DecimalMin("0")
    private Double multiSaturatedFattyAcids;

    @DecimalMin("0")
    private Double cholesterol;

    @DecimalMin("0")
    private Double saccharose;

    @DecimalMin("0")
    private Double cellulose;

    @DecimalMin("0")
    private Double calcium;

    @DecimalMin("0")
    private Double magnesium;

    @DecimalMin("0")
    private Double iron;

    @DecimalMin("0")
    private Double zinc;

    @DecimalMin("0")
    private Double vitaminD;

    @DecimalMin("0")
    private Double vitaminB1;

    @DecimalMin("0")
    private Double vitaminB2;

    @DecimalMin("0")
    private Double vitaminPp;

    @DecimalMin("0")
    private Double vitaminB6;

    @DecimalMin("0")
    private Double folicAcid;

    @DecimalMin("0")
    private Double vitaminB12;

    @DecimalMin("0")
    private Double vitaminC;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = true)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", nullable = false, updatable = true)})
    @JsonIgnore
    private Set<ProductCategory> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private Set<Like> likes = new LinkedHashSet<>();

    @Column(name = "likes_plus")
    @JsonIgnore
    private int likesPlus;

    @Column(name = "likes_minus")
    @JsonIgnore
    private int likesMinus;

    @Transient
    private Double energy;

    @Transient
    private Double amountOfCarbohydrateExchangers;

    @Transient
    private Double amountOfProteinExchangers;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime addDate;

    public Product() {

    }

    public Product(Long id) {

        super(id);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Double getFat() {

        return fat;
    }

    public void setFat(Double fat) {

        this.fat = fat;
    }

    public Double getProtein() {

        return protein;
    }

    public void setProtein(Double protein) {

        this.protein = protein;
    }

    public Double getCarbohydrates() {

        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {

        this.carbohydrates = carbohydrates;
    }

    public Double getWeightForOneWw() {

        return weightForOneWw;
    }

    public void setWeightForOneWw(Double weightForOneWw) {

        this.weightForOneWw = weightForOneWw;
    }

    public String getHomeMeasure() {

        return homeMeasure;
    }

    public void setHomeMeasure(String homeMeasure) {

        this.homeMeasure = homeMeasure;
    }

    public Integer getWwInPortion() {

        return wwInPortion;
    }

    public void setWwInPortion(Integer wwInPortion) {

        this.wwInPortion = wwInPortion;
    }

    public UserData getAuthor() {

        return author;
    }

    public void setAuthor(UserData author) {

        this.author = author;
    }

    public boolean isModerated() {

        return moderated;
    }

    public void setModerated(boolean moderated) {

        this.moderated = moderated;
    }

    public String getProductNormalizedName() {

        return productNormalizedName;
    }

    public void setProductNormalizedName(String productNormalizedName) {

        this.productNormalizedName = productNormalizedName;
    }

    public boolean isAllergen() {

        return allergen;
    }

    public void setAllergen(boolean allergen) {

        this.allergen = allergen;
    }

    public boolean isGlutenFree() {

        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {

        this.glutenFree = glutenFree;
    }

    public Double getSaturatedFattyAcids() {

        return saturatedFattyAcids;
    }

    public void setSaturatedFattyAcids(Double saturatedFattyAcids) {

        this.saturatedFattyAcids = saturatedFattyAcids;
    }

    public Double getOneSaturatedFattyAcids() {

        return oneSaturatedFattyAcids;
    }

    public void setOneSaturatedFattyAcids(Double oneSaturatedFattyAcids) {

        this.oneSaturatedFattyAcids = oneSaturatedFattyAcids;
    }

    public Double getMultiSaturatedFattyAcids() {

        return multiSaturatedFattyAcids;
    }

    public void setMultiSaturatedFattyAcids(Double multiSaturatedFattyAcids) {

        this.multiSaturatedFattyAcids = multiSaturatedFattyAcids;
    }

    public Double getCholesterol() {

        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {

        this.cholesterol = cholesterol;
    }

    public Double getSaccharose() {

        return saccharose;
    }

    public void setSaccharose(Double saccharose) {

        this.saccharose = saccharose;
    }

    public Double getCellulose() {

        return cellulose;
    }

    public void setCellulose(Double cellulose) {

        this.cellulose = cellulose;
    }

    public Double getCalcium() {

        return calcium;
    }

    public void setCalcium(Double calcium) {

        this.calcium = calcium;
    }

    public Double getMagnesium() {

        return magnesium;
    }

    public void setMagnesium(Double magnesium) {

        this.magnesium = magnesium;
    }

    public Double getIron() {

        return iron;
    }

    public void setIron(Double iron) {

        this.iron = iron;
    }

    public Double getZinc() {

        return zinc;
    }

    public void setZinc(Double zinc) {

        this.zinc = zinc;
    }

    public Double getVitaminD() {

        return vitaminD;
    }

    public void setVitaminD(Double vitamin_d) {

        this.vitaminD = vitamin_d;
    }

    public Double getVitaminB1() {

        return vitaminB1;
    }

    public void setVitaminB1(Double vitamin_b1) {

        this.vitaminB1 = vitamin_b1;
    }

    public Double getVitaminB2() {

        return vitaminB2;
    }

    public void setVitaminB2(Double vitamin_b2) {

        this.vitaminB2 = vitamin_b2;
    }

    public Double getVitaminPp() {

        return vitaminPp;
    }

    public void setVitaminPp(Double vitamin_pp) {

        this.vitaminPp = vitamin_pp;
    }

    public Double getVitaminB6() {

        return vitaminB6;
    }

    public void setVitaminB6(Double vitamin_b6) {

        this.vitaminB6 = vitamin_b6;
    }

    public Double getFolicAcid() {

        return folicAcid;
    }

    public void setFolicAcid(Double folate) {

        this.folicAcid = folate;
    }

    public Double getVitaminB12() {

        return vitaminB12;
    }

    public void setVitaminB12(Double vitamin_b12) {

        this.vitaminB12 = vitamin_b12;
    }

    public Double getVitaminC() {

        return vitaminC;
    }

    public void setVitaminC(Double vitamin_c) {

        this.vitaminC = vitamin_c;
    }

    public Set<ProductCategory> getCategories() {

        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {

        this.categories.clear();
        this.categories.addAll(categories);
    }

    public Double getEnergy() {

        return energy;
    }

    public void setEnergy(Double energy) {

        this.energy = energy;
    }

    public Double getAmountOfCarbohydrateExchangers() {

        return amountOfCarbohydrateExchangers;
    }

    public void setAmountOfCarbohydrateExchangers(Double amountOfCarbohydrateExchangers) {

        this.amountOfCarbohydrateExchangers = amountOfCarbohydrateExchangers;
    }

    public Double getAmountOfProteinExchangers() {

        return amountOfProteinExchangers;
    }

    public void setAmountOfProteinExchangers(Double amountOfProteinExchangers) {

        this.amountOfProteinExchangers = amountOfProteinExchangers;
    }

    public Set<Like> getLikes() {

        return likes;
    }

    public void setLikes(Set<Like> likes) {

        this.likes = likes;
    }

    public int getLikesPlus() {

        return likesPlus;
    }

    public void setLikesPlus(int likesPlus) {

        this.likesPlus = likesPlus;
    }

    public int getLikesMinus() {

        return likesMinus;
    }

    public void setLikesMinus(int likesMinus) {

        this.likesMinus = likesMinus;
    }

    public DateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(DateTime addDate) {
        this.addDate = addDate;
    }
}
