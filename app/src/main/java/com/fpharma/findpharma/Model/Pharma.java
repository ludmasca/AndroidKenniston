package com.fpharma.findpharma.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Pharma implements Serializable {
    private String codCnes;
    private String codUnidade;
    private String codIbge;
    private String cnpj;
    private String nomeFantasia;
    private String natureza;
    private String tipoUnidade;
    private String retencao;
    private String fluxoClientela;
    private String origemGeografica;
    private String temAtendimentoUrgencia;
    private String vinculoSus;
    private String esferaAdministrativa;
    private String temAtendimentoAmbulatorial;
    private String temCentroCirurgico;
    private String temObstetra;
    private String temNeoNatal;
    private String temDialise;
    private String descricaoCompleta;
    private String tipoUnidadeCnes;
    private String categoriaUnidade;
    private String turnoAtendimento;
    private Double lat;
    @SerializedName("long")
    private Double longitude;

    public Pharma() {
    }

    public Pharma(String codCnes, String codUnidade, String codIbge, String cnpj, String nomeFantasia, String natureza, String tipoUnidade, String retencao, String fluxoClientela, String origemGeografica, String temAtendimentoUrgencia, String vinculoSus, String esferaAdministrativa, String temAtendimentoAmbulatorial, String temCentroCirurgico, String temObstetra, String temNeoNatal, String temDialise, String descricaoCompleta, String tipoUnidadeCnes, String categoriaUnidade, String turnoAtendimento, Double lat, Double longitude) {
        this.codCnes = codCnes;
        this.codUnidade = codUnidade;
        this.codIbge = codIbge;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.natureza = natureza;
        this.tipoUnidade = tipoUnidade;
        this.retencao = retencao;
        this.fluxoClientela = fluxoClientela;
        this.origemGeografica = origemGeografica;
        this.temAtendimentoUrgencia = temAtendimentoUrgencia;
        this.vinculoSus = vinculoSus;
        this.esferaAdministrativa = esferaAdministrativa;
        this.temAtendimentoAmbulatorial = temAtendimentoAmbulatorial;
        this.temCentroCirurgico = temCentroCirurgico;
        this.temObstetra = temObstetra;
        this.temNeoNatal = temNeoNatal;
        this.temDialise = temDialise;
        this.descricaoCompleta = descricaoCompleta;
        this.tipoUnidadeCnes = tipoUnidadeCnes;
        this.categoriaUnidade = categoriaUnidade;
        this.turnoAtendimento = turnoAtendimento;
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getCodCnes() {
        return codCnes;
    }

    public void setCodCnes(String codCnes) {
        this.codCnes = codCnes;
    }

    public String getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(String codUnidade) {
        this.codUnidade = codUnidade;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(String codIbge) {
        this.codIbge = codIbge;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getRetencao() {
        return retencao;
    }

    public void setRetencao(String retencao) {
        this.retencao = retencao;
    }

    public String getFluxoClientela() {
        return fluxoClientela;
    }

    public void setFluxoClientela(String fluxoClientela) {
        this.fluxoClientela = fluxoClientela;
    }

    public String getOrigemGeografica() {
        return origemGeografica;
    }

    public void setOrigemGeografica(String origemGeografica) {
        this.origemGeografica = origemGeografica;
    }

    public String getTemAtendimentoUrgencia() {
        return temAtendimentoUrgencia;
    }

    public void setTemAtendimentoUrgencia(String temAtendimentoUrgencia) {
        this.temAtendimentoUrgencia = temAtendimentoUrgencia;
    }

    public String getVinculoSus() {
        return vinculoSus;
    }

    public void setVinculoSus(String vinculoSus) {
        this.vinculoSus = vinculoSus;
    }

    public String getEsferaAdministrativa() {
        return esferaAdministrativa;
    }

    public void setEsferaAdministrativa(String esferaAdministrativa) {
        this.esferaAdministrativa = esferaAdministrativa;
    }

    public String getTemAtendimentoAmbulatorial() {
        return temAtendimentoAmbulatorial;
    }

    public void setTemAtendimentoAmbulatorial(String temAtendimentoAmbulatorial) {
        this.temAtendimentoAmbulatorial = temAtendimentoAmbulatorial;
    }

    public String getTemCentroCirurgico() {
        return temCentroCirurgico;
    }

    public void setTemCentroCirurgico(String temCentroCirurgico) {
        this.temCentroCirurgico = temCentroCirurgico;
    }

    public String getTemObstetra() {
        return temObstetra;
    }

    public void setTemObstetra(String temObstetra) {
        this.temObstetra = temObstetra;
    }

    public String getTemNeoNatal() {
        return temNeoNatal;
    }

    public void setTemNeoNatal(String temNeoNatal) {
        this.temNeoNatal = temNeoNatal;
    }

    public String getTemDialise() {
        return temDialise;
    }

    public void setTemDialise(String temDialise) {
        this.temDialise = temDialise;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public String getTipoUnidadeCnes() {
        return tipoUnidadeCnes;
    }

    public void setTipoUnidadeCnes(String tipoUnidadeCnes) {
        this.tipoUnidadeCnes = tipoUnidadeCnes;
    }

    public String getCategoriaUnidade() {
        return categoriaUnidade;
    }

    public void setCategoriaUnidade(String categoriaUnidade) {
        this.categoriaUnidade = categoriaUnidade;
    }

    public String getTurnoAtendimento() {
        return turnoAtendimento;
    }

    public void setTurnoAtendimento(String turnoAtendimento) {
        this.turnoAtendimento = turnoAtendimento;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
