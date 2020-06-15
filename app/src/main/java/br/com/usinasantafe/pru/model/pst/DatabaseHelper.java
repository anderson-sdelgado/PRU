package br.com.usinasantafe.pru.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pru_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;
		
		// TODO Auto-generated constructor stub
		instance = this;
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		
		try{


			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.FuncBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.LiderBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.OSBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.TipoApontBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.DataHoraBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.estaticas.ParadaBean.class);

			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.variaveis.ApontBean.class);
			TableUtils.createTable(cs, br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				//TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
