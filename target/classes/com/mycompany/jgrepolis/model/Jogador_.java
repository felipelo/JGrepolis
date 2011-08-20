package com.mycompany.jgrepolis.model;

import com.mycompany.jgrepolis.model.Classificacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-05-07T19:34:11")
@StaticMetamodel(Jogador.class)
public class Jogador_ { 

    public static volatile SingularAttribute<Jogador, Integer> playerId;
    public static volatile SingularAttribute<Jogador, String> name;
    public static volatile ListAttribute<Jogador, Classificacao> classificacaoList;

}