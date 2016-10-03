//
//  AlgorithmListViewController.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/3/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import UIKit

let SW = UIScreen.main.bounds.width
let SH = UIScreen.main.bounds.height

let cellIndentifier = "cellId"

class AlgorithmListViewController: UIViewController, UITableViewDataSource, UITableViewDelegate
{
    let titles = ["Complete Graph", "Sparse Graph"]
    var tableView = UITableView()

    override func viewDidLoad() {
        super.viewDidLoad()

        setup()
    }

    func setup()
    {
        tableView = UITableView.init(frame: CGRect(x: 0, y: 0, width: SW, height: SH), style: .plain)
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: cellIndentifier)
        self.view.addSubview(tableView)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return titles.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIndentifier)
        cell?.textLabel?.text = titles[indexPath.row]
        return cell!
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 70
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch indexPath.row {
        case 0:
            let completeViewController = CompleteGraphViewController()
            self.navigationController?.pushViewController(completeViewController, animated: true)
        case 1:
            let sparseViewController = SparseGraphViewController()
            self.navigationController?.pushViewController(sparseViewController, animated: true)
        default: break
        }
    }
}
