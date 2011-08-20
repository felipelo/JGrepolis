package com.mycompany.jgrepolis.model;

import com.mycompany.jgrepolis.model.ClassificacaoPK;
import com.mycompany.jgrepolis.model.Jogador;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-05-07T19:34:11")
@StaticMetamodel(Classificacao.class)
public class Classificacao_ { 

    public static volatile SingularAttribute<Classificacao, Integer> rank;
    public static volatile SingularAttribute<Classificacao, String> ally;
    public static volatile SingularAttribute<Classificacao, Jogador> jogador;
    public static volatile SingularAttribute<Classificacao, Integer> cities;
    public static volatile SingularAttribute<Classificacao, ClassificacaoPK> classificacaoPK;
    public static volatile SingularAttribute<Classificacao, Short> ocean;
    public static volatile SingularAttribute<Classificacao, Integer> points;
    public static volatile SingularAttribute<Classificacao, Integer> pointsAvg;

}