package br.com.bruno2code.contrateai.model.places;

public class PlusCode {

    private String compound_code;
    private String global_code;

    public String getCompound_code() {
        return compound_code;
    }

    public void setCompound_code(String compound_code) {
        this.compound_code = compound_code;
    }

    public String getGlobal_code() {
        return global_code;
    }

    public void setGlobal_code(String global_code) {
        this.global_code = global_code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlusCode{");
        sb.append("compound_code=").append(compound_code);
        sb.append(", global_code=").append(global_code);
        sb.append('}');
        return sb.toString();
    }

}
