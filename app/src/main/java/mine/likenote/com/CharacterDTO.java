package mine.likenote.com;

public class CharacterDTO {
    private int charIndex;
    private String charName;
    private String charSex;
    private int charImg;
    private String charAge;
    private String charCm;
    private String charKg;
    private String charContent;
    private String charTag;
    private boolean charLikeChk;
    private String charCreateDate;
    private String charStar;

    public CharacterDTO(int charIndex, String charName, String charSex, int charImg, String charAge, String charCm, String charKg,
                        String charContent, String charTag, String charLikeChk, String charCreateDate, String charStar) {
        this.charIndex = charIndex;
        this.charName = charName;
        this.charSex = charSex;
        this.charImg = charImg;
        this.charAge = charAge;
        this.charCm = charCm;
        this.charKg = charKg;
        this.charContent = charContent;
        this.charTag = charTag;
        this.charLikeChk = charLikeChk;
        this.charCreateDate = charCreateDate;
        this.charStar = charStar;
    }



    public int getCharIndex() {
        return charIndex;
    }

    public void setCharIndex(int charIndex) {
        this.charIndex = charIndex;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCharSex() {
        return charSex;
    }

    public void setCharSex(String charSex) {
        this.charSex = charSex;
    }

    public int getCharImg() {
        return charImg;
    }

    public void setCharImg(int charImg) {
        this.charImg = charImg;
    }

    public String getCharAge() {
        return charAge;
    }

    public void setCharAge(String charAge) {
        this.charAge = charAge;
    }

    public String getCharCm() {
        return charCm;
    }

    public void setCharCm(String charCm) {
        this.charCm = charCm;
    }

    public String getCharKg() {
        return charKg;
    }

    public void setCharKg(String charKg) {
        this.charKg = charKg;
    }

    public String getCharContent() {
        return charContent;
    }

    public void setCharContent(String charContent) {
        this.charContent = charContent;
    }

    public String getCharTag() {
        return charTag;
    }

    public void setCharTag(String charTag) {
        this.charTag = charTag;
    }

    public boolean isCharLikeChk() {
        return charLikeChk;
    }

    public void setCharLikeChk(boolean charLikeChk) {
        this.charLikeChk = charLikeChk;
    }

    public String getCharCreateDate() {
        return charCreateDate;
    }

    public void setCharCreateDate(String charCreateDate) {
        this.charCreateDate = charCreateDate;
    }

    public String getCharStar() {
        return charStar;
    }

    public void setCharStar(String charStar) {
        this.charStar = charStar;
    }


}
