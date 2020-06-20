package br.com.furb.trabalhofinal.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import br.com.furb.trabalhofinal.model.BasePojo;

/**
 * Serviço padrão de acesso e utilização do Firebase Firestore.
 *
 * @param <P> pojo a ser gerenciado no Firestore
 */
public abstract class FirestoreService<P extends BasePojo> {

    private static final String TAG = "FirestoreService";
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    /**
     * @return nome da coleção onde serão persistidos os documentos
     */
    protected abstract String getCollectionName();

    /**
     * Atualiza as informações de um documento com base no seu id.
     *
     * @param pojo documento a ser atualizado
     * @param id   id do documento a ser atualizado
     * @return tarefa responsável por inserir o documento. Podendo ser tratado evento de sucesso e erro
     */
    public Task<Void> set(P pojo, String id) {
        return firestore.collection(getCollectionName())
                .document(id)
                .set(pojo)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Erro ao adicionar/atualizar documento: ", e);
                    }
                });
    }

    /**
     * Inclui um documento na coleção definida.<br>
     * O id do documento será gerado pelo Firestore e setado no POJO.
     *
     * @param pojo documento a ser persistido
     * @return tarefa responsável por inserir o documento. Podendo ser tratado evento de sucesso e erro
     */
    public Task<Void> put(P pojo) {
        String id = firestore.collection(getCollectionName()).document().getId();
        pojo.setId(id);
        return set(pojo, id);
    }

    /**
     * Remove um documento da coleção definida.
     *
     * @param id identificado do documento a ser removido
     * @return tarefa responsável por inserir o documento. Podendo ser tratado evento de sucesso e erro
     */
    public Task<Void> remove(String id) {
        return firestore.collection(getCollectionName()).document(id)
                .delete()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Erro ao remover documento: ", e);
                    }
                });
    }

    /**
     * Recupera todos os documentos da coleção definida.
     *
     * @return tarefa contendo os documentos recuperados
     */
    public Task<QuerySnapshot> getAll() {
        return firestore.collection(getCollectionName())
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Erro ao recuperar documentos: ", e);
                    }
                });
    }

}
