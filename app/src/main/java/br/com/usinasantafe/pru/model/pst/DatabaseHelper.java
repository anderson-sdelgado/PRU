package br.com.usinasantafe.pru.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.LiderBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pru.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pru_db";
	public static final int FORCA_BD_VERSION = 4;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);;

		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, LiderBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, RFuncaoAtivParBean.class);
			TableUtils.createTable(cs, ROSAtivBean.class);
			TableUtils.createTable(cs, TipoApontBean.class);
			TableUtils.createTable(cs, TurmaBean.class);

			TableUtils.createTable(cs, AlocaFuncBean.class);
			TableUtils.createTable(cs, ApontRuricolaBean.class);
			TableUtils.createTable(cs, BoletimRuricolaBean.class);
			TableUtils.createTable(cs, ConfigBean.class);

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

			if((oldVersion <= 2) && (newVersion > 2)){

				dropTables(cs);
				createTables(cs);

			} else if((oldVersion <= 3) && (newVersion > 3)){

				dropTables(cs);
				createTables(cs);

			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

	public void createTables(ConnectionSource cs) {

		try {

			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, LiderBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, RFuncaoAtivParBean.class);
			TableUtils.createTable(cs, ROSAtivBean.class);
			TableUtils.createTable(cs, TipoApontBean.class);
			TableUtils.createTable(cs, TurmaBean.class);

			TableUtils.createTable(cs, AlocaFuncBean.class);
			TableUtils.createTable(cs, ApontRuricolaBean.class);
			TableUtils.createTable(cs, BoletimRuricolaBean.class);
			TableUtils.createTable(cs, ConfigBean.class);

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

	public void dropTables(ConnectionSource cs) {

		try {

			TableUtils.dropTable(cs, AtividadeBean.class, true);
			TableUtils.dropTable(cs, FuncBean.class, true);
			TableUtils.dropTable(cs, LiderBean.class, true);
			TableUtils.dropTable(cs, OSBean.class, true);
			TableUtils.dropTable(cs, ParadaBean.class, true);
			TableUtils.dropTable(cs, RFuncaoAtivParBean.class, true);
			TableUtils.dropTable(cs, ROSAtivBean.class, true);
			TableUtils.dropTable(cs, TipoApontBean.class, true);
			TableUtils.dropTable(cs, TurmaBean.class, true);

			TableUtils.dropTable(cs, AlocaFuncBean.class, true);
			TableUtils.dropTable(cs, ApontRuricolaBean.class, true);
			TableUtils.dropTable(cs, BoletimRuricolaBean.class, true);
			TableUtils.dropTable(cs, ConfigBean.class, true);

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

}
