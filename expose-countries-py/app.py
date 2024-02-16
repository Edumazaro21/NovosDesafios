import requests

def buscar_paises_por_regiao_e_palavra_chave(region, keyword):
    url_base = 'http://localhost:8000/regions/search'
    paises = []

    pagina_atual = 1
    total_paginas = 1

    while pagina_atual <= total_paginas:
        url = f"{url_base}?region={region}&key_word={keyword}&page={pagina_atual}"

        resposta = requests.get(url)
        if resposta.status_code == 200:
            dados = resposta.json()

            total_paginas = dados['total_pages']

            for registro in dados['data']:
                pais = {
                    'nome': registro['name'],
                    'população': registro['population']
                }
                paises.append(pais)

            pagina_atual += 1
        else:
            print("Erro ao buscar dados:", resposta.status_code)
            break

    return paises


if __name__ == '__main__':
    region = 'EuropaOcidental'
    keyword = 1
    paises = buscar_paises_por_regiao_e_palavra_chave(region, keyword)
    print(paises)