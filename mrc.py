import transformers
from transformers import AutoTokenizer, AutoModelForQuestionAnswering, pipeline
# import argparse
import json

def main():
    #parser = argparse.ArgumentParser()

    # # Required parameters
    # parser.add_argument(
    #     "--question",
    #     default=None,
    #     type=str,
    #     required=True,
    #     help="The Question",
    # )
    # parser.add_argument(
    #     "--context",
    #     default=None,
    #     type=str,
    #     required=True, 
    #     help="The Context of the Question",
    # )
    # args=parser.parse_args()
    # tokenizer = AutoTokenizer.from_pretrained("./distilbert-base-cased/")
    # model = AutoModelForQuestionAnswering.from_pretrained("./distilbert-base-cased/")
    classifier=pipeline("question-answering")
    context="He lives in London"
    question="Where does he live"
    # result=classifier(question=args.question, context=args.context)
    with open('tmp.json', 'r') as f:
        Q = json.load(f)
    # result=classifier(question=question, context=context)
    result=classifier(question=Q['question'], context=Q['context'])
    print(result['answer'])
    with open("tmp.json", "w") as f:
        tmp = json.dumps(result)
        f.write(tmp)
    return result['answer']
 
if __name__ == "__main__":
    main()
