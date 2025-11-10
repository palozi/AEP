    package com.aep.inovalocal.service;

    import com.aep.inovalocal.model.Deslocamento;
    import com.aep.inovalocal.model.Recompensa;
    import com.aep.inovalocal.model.Usuario;
    import com.aep.inovalocal.repository.DeslocamentoRepository;
    import com.aep.inovalocal.repository.RecompensaRepository;
    import com.aep.inovalocal.repository.UsuarioRepository;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.NoSuchElementException;

    @Service
    public class MobilidadeService {

        private final DeslocamentoRepository desRepo;
        private final RecompensaRepository recoRepo;
        private final UsuarioRepository usuarioRepo;

        public MobilidadeService(DeslocamentoRepository desRepo,
                                 RecompensaRepository recoRepo,
                                 UsuarioRepository usuarioRepo) {
            this.desRepo = desRepo;
            this.recoRepo = recoRepo;
            this.usuarioRepo = usuarioRepo;
        }

        @Transactional
        public Usuario criarUsuario(String nome, String email, String senha){
            Usuario u = new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(senha);
            u.setPontos(0);
            return usuarioRepo.save(u);
        }

        public List<Usuario> listarUsuarios(){
            return usuarioRepo.findAll();
        }

        @Transactional
        public Deslocamento registrarDeslocamento(Long usuarioId,
                                                  Deslocamento.TipoTransporte tipo,
                                                  double distancia){
            Usuario u = usuarioRepo.findById(usuarioId)
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado: " + usuarioId));

            int pontos = pontosPorTipo(tipo);

            Deslocamento d = new Deslocamento();
            d.setUsuario(u);
            d.setTipo(tipo);
            d.setDistancia(distancia); // nome do campo consistente
            d.setPontosGanhos(pontos);

            Deslocamento salvo = desRepo.save(d);

            u.setPontos(u.getPontos() + pontos);
            usuarioRepo.save(u);

            return salvo;
        }

        public List<Recompensa> listarRecompensas(){
            return recoRepo.findAll();
        }

        @Transactional
        public boolean resgatarRecompensa(Long usuarioId, Long recompensaId){
            Usuario u = usuarioRepo.findById(usuarioId)
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado: " + usuarioId));
            Recompensa r = recoRepo.findById(recompensaId)
                    .orElseThrow(() -> new NoSuchElementException("Recompensa não encontrada: " + recompensaId));

            if (u.getPontos() < r.getCustoPontos()){
                return false;
            }

            u.setPontos(u.getPontos() - r.getCustoPontos()); // debita de verdade
            usuarioRepo.save(u);
            return true;
        }

        private int pontosPorTipo(Deslocamento.TipoTransporte tipo){
            return switch (tipo){
                case CAMINHADA -> 5;
                case BICICLETA -> 10;
                case ONIBUS -> 7;
            };
        }
    }
