#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time : 2021/11/24 上午11:06
# @Author : HuangBenHao
import joblib
import torch
import torch.nn as nn
import numpy as np
import torch.nn.functional as F
import sys

# scaler_path = r'/Users/lazyben/Projects/exercise-prescription-system/src/main/resources/min_max_scaler.pkl'
# model_state_dict_path = r'/Users/lazyben/Downloads/human_body_classification/checkpoints/_NN_epoch88_1109_16_19_13.pth'

scaler_path = r'/Users/lazyben/Projects/exercise-prescription-system/src/main/resources/min_max_scaler.pkl'
model_state_dict_path = r'/Users/lazyben/Projects/exercise-prescription-system/src/main/resources/_NN_epoch88_1109_16_19_13.pth'

class Config(object):
    input_dim = 12
    relation_type = 11
    hidden_dim = 128


config = Config()


class NN(nn.Module):
    def __init__(self, config: Config):
        """
        NN 模型
        :param config: 配置文件
        """
        super(NN, self).__init__()

        # self.dropout: float = config.dropout
        self.input_dim = config.input_dim
        self.output_dim = config.relation_type
        self.hidden_dim = config.hidden_dim
        self.fc1 = nn.Linear(self.input_dim, self.hidden_dim)
        self.fc2 = nn.Linear(self.hidden_dim, self.hidden_dim)
        self.fc3 = nn.Linear(self.hidden_dim, self.output_dim)

    def forward(self, input_data):
        x = F.relu(self.fc1(input_data))
        x = F.relu(self.fc2(x))
        x = self.fc3(x)
        return x


def predict(data):
    scaler = joblib.load(scaler_path)
    model = NN(config)
    model.load_state_dict(torch.load(model_state_dict_path))
    data = scaler.transform(data).astype(np.float32)
    return torch.argmax(model(torch.tensor(data))).item()


if __name__ == '__main__':
    print(predict([[float(i) for i in sys.argv[1:]]]))